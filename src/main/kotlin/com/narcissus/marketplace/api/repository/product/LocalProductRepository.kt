package com.narcissus.marketplace.api.repository.product

import com.narcissus.marketplace.api.model.Product
import com.narcissus.marketplace.api.model.ProductPreview

interface LocalProductRepository {
    fun getProducts(limit: Int, page: Int): List<ProductPreview>
    fun insertAll(products: List<Product>)
    fun deleteAll()
    fun getSimilarProducts(id: String): List<Product>
}