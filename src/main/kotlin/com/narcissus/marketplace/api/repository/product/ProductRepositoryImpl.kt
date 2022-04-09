package com.narcissus.marketplace.api.repository.product

import com.narcissus.marketplace.api.data.EntityProduct
import com.narcissus.marketplace.api.data.EntityReview
import com.narcissus.marketplace.api.data.EntitySimilarProducts
import com.narcissus.marketplace.api.data.SimilarProducts
import com.narcissus.marketplace.api.data.db.createMissingTablesAndColumns
import com.narcissus.marketplace.api.data.db.dropTables
import com.narcissus.marketplace.api.model.Product
import com.narcissus.marketplace.api.model.ProductDetails
import com.narcissus.marketplace.api.model.ProductPreview
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.math.min

class ProductRepositoryImpl : ProductRepository {

    override fun getProducts(limit: Int, page: Int): List<ProductPreview> =
        paginate(limit, page)

    override fun getRandomProducts(limit: Int, page: Int): List<ProductPreview> =
        paginate(limit, page) { products ->
            products
                .shuffled()
        }

    override fun getTopRatedProducts(limit: Int, page: Int): List<ProductPreview> =
        paginate(limit, page) { products ->
            products
                .sortedByDescending { it.rating }
        }

    override fun getTopSalesProducts(limit: Int, page: Int): List<ProductPreview> =
        paginate(limit, page) { products ->
            products
                .sortedByDescending { it.sales }
        }

    override fun searchProducts(query: String, limit: Int, page: Int): List<ProductPreview> =
        paginate(limit, page) { products ->
            products
                .filter { it.name.contains(query, ignoreCase = true) }
        }

    override fun searchProductsTopRated(query: String, limit: Int, page: Int): List<ProductPreview> =
        paginate(limit, page) { products ->
            products
                .sortedByDescending { it.rating }
                .filter { it.name.contains(query, ignoreCase = true) }
        }

    override fun searchProductsTopSales(query: String, limit: Int, page: Int): List<ProductPreview> =
        paginate(limit, page) { products ->
            products
                .sortedByDescending { it.sales }
                .filter { it.name.contains(query, ignoreCase = true) }
        }

    private inline fun paginate(
        limit: Int,
        page: Int,
        crossinline transform: (List<EntityProduct>) -> List<EntityProduct> = { it }
    ): List<ProductPreview> = transaction {
        val result = EntityProduct.all()
            .toList()
            .let {
                transform(it)
            }

        val count = result.size
        val start = min(count, (page - 1) * limit)
        val end = min(count, page * limit)

        result
            .slice(start until end)
            .map(EntityProduct::toProduct)
            .map(Product::toProductPreview)
    }

    override fun getProductDetails(productId: String): ProductDetails = transaction {
        EntityProduct.findById(UUID.fromString(productId))
            ?.toProduct()
            ?.toProductDetails()
            ?: throw IllegalArgumentException("Could not find product by id")
    }

    override fun insertAll(products: List<Product>) = transaction {
        products.forEach {
            insertProduct(it)
            insertReviews(it)
            insertSimilarProducts(it)
        }
    }

    private fun insertProduct(product: Product) {
        EntityProduct.new(UUID.fromString(product.id)) {
            name = product.name
            productImg150 = product.productImg150
            productImg300 = product.productImg300
            productImg600 = product.productImg600
            price = product.price
            type = product.type
            departmentName = product.departmentName
            departmentId = product.departmentId
            inStock = product.inStock
            color = product.color
            material = product.material
            rating = product.rating
            sales = product.sales
            description = product.description
        }
    }

    private fun insertReviews(product: Product) {
        product.reviews.forEach { review ->
            EntityReview.new(UUID.fromString(review.reviewId)) {
                this.product = EntityProduct[UUID.fromString(review.productId)]
                author = review.author
                details = review.details
                rating = review.rating
                avatar = review.reviewAuthorIcon
            }
        }
    }

    private fun insertSimilarProducts(product: Product) {
        product.similarProductIds.forEach {
            EntitySimilarProducts.new {
                this.product = EntityProduct[UUID.fromString(product.id)]
                similarProductId = it
            }
        }
    }

    override fun deleteAll() = transaction {
        dropTables()
        createMissingTablesAndColumns()
    }

    override fun getSimilarProducts(id: String): List<Product> = transaction {
        EntitySimilarProducts.find {
            SimilarProducts.product eq UUID.fromString(id)
        }.map { entitySimilarProducts ->
            EntityProduct.findById(UUID.fromString(entitySimilarProducts.similarProductId))
        }.mapNotNull { entityProduct ->
            entityProduct?.toProduct()
        }
    }
}