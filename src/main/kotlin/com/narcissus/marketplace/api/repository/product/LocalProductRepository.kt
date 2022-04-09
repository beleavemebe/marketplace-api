package com.narcissus.marketplace.api.repository.product

import com.narcissus.marketplace.api.model.Product

interface LocalProductRepository {
    fun insertAll(products: List<Product>)
    fun deleteAll()
    fun getSimilarProducts(id: String): List<Product>
}