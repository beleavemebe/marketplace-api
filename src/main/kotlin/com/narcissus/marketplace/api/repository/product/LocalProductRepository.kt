package com.narcissus.marketplace.api.repository.product

import com.narcissus.marketplace.api.model.Product
import com.narcissus.marketplace.api.model.ProductDetails
import com.narcissus.marketplace.api.model.ProductPreview

interface LocalProductRepository {
    fun getProducts(limit: Int, page: Int): List<ProductPreview>
    fun getRandomProducts(limit: Int, page: Int): List<ProductPreview>
    fun getTopRatedProducts(limit: Int, page: Int): List<ProductPreview>
    fun getTopSalesProducts(limit: Int, page: Int): List<ProductPreview>
    fun getProductDetails(productId: String): ProductDetails
    fun insertAll(products: List<Product>)
    fun deleteAll()
    fun getSimilarProducts(id: String): List<Product>
}