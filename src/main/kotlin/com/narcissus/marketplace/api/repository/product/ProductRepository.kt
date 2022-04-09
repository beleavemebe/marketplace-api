package com.narcissus.marketplace.api.repository.product

import com.narcissus.marketplace.api.Config
import com.narcissus.marketplace.api.model.Product
import com.narcissus.marketplace.api.repository.product.dummyproductsapi.DummyProductsServiceImpl
import io.ktor.client.*

interface ProductRepository {
    suspend fun getProducts(limit: Int, page: Int): String
    suspend fun getTopRatedProducts(limit: Int, page: Int): String
    suspend fun getTopSalesProducts(limit: Int, page: Int): String
    suspend fun getRandomProducts(limit: Int, page: Int): String
    suspend fun getProductDetails(productId: String, similarities: String): String
    suspend fun searchProducts(query: String, page: Int, perPage: Int): String
    suspend fun searchProductsTopRated(query: String, page: Int, perPage: Int): String
    suspend fun searchProductsTopSales(query: String, page: Int, perPage: Int): String
    fun insertAll(products: List<Product>)
    fun deleteAll()

    companion object {
        fun newInstance(): ProductRepository {
            return ProductRepositoryImpl(
                apiService = DummyProductsServiceImpl(
                    apiKey = Config::apiKey,
                    httpClient = HttpClient()
                )
            )
        }
    }
}