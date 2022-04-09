package com.narcissus.marketplace.api.repository.product

interface RemoteProductRepository {
    suspend fun searchProducts(query: String, page: Int, perPage: Int): String
    suspend fun searchProductsTopRated(query: String, page: Int, perPage: Int): String
    suspend fun searchProductsTopSales(query: String, page: Int, perPage: Int): String
}