package com.narcissus.marketplace.api.source.dummyproducts

import com.narcissus.marketplace.api.Config
import com.narcissus.marketplace.api.model.OrderRequest
import io.ktor.client.*

interface DummyProductsService {
    suspend fun getProducts(limit: Int, page: Int): String

    suspend fun searchProducts(query: String): String

    suspend fun getProductDetails(productId: String, similarities: String = "true"): String

    suspend fun getRandomProducts(limit: Int, page: Int): String

    suspend fun getTopRatedProducts(limit: Int, page: Int): String

    suspend fun getTopSalesProducts(limit: Int, page: Int): String

    suspend fun checkout(orderRequest: OrderRequest): String

    companion object {
        fun newInstance(): DummyProductsService {
            return DummyProductsServiceImpl(
                apiKey = Config::apiKey,
                httpClient = HttpClient()
            )
        }
    }
}