package com.narcissus.marketplace.api.repository.product

import com.narcissus.marketplace.api.Config
import com.narcissus.marketplace.api.data.EntityProduct
import com.narcissus.marketplace.api.data.EntityReview
import com.narcissus.marketplace.api.data.EntitySimilarProducts
import com.narcissus.marketplace.api.data.SimilarProducts
import com.narcissus.marketplace.api.data.db.createMissingTablesAndColumns
import com.narcissus.marketplace.api.data.db.dropTables
import com.narcissus.marketplace.api.model.Product
import com.narcissus.marketplace.api.model.response.ProductPreviewsResponse
import com.narcissus.marketplace.api.repository.product.dummyproductsapi.DummyProductsServiceImpl
import io.ktor.client.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProductRepository(
    private val apiService: DummyProductsServiceImpl
) : LocalProductRepository, RemoteProductRepository by apiService {
    companion object {
        fun newInstance() = ProductRepository(
            apiService = DummyProductsServiceImpl(
                apiKey = Config::apiKey,
                httpClient = HttpClient()
            )
        )
    }

    override suspend fun getProducts(limit: Int, page: Int): String {
        return transaction {
            EntityProduct.all().toList().map(EntityProduct::toProduct)
        }.let { products ->
            ProductPreviewsResponse(products.map(Product::toProductPreview))
        }.run {
            Json.encodeToString(this)
        }
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