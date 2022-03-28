package com.narcissus.marketplace.api.source.dummyproducts

import com.narcissus.marketplace.api.Config
import com.narcissus.marketplace.api.model.OrderRequest
import com.narcissus.marketplace.api.source.dummyproducts.actual.DummyProductsServiceImpl
import com.narcissus.marketplace.api.source.dummyproducts.hardcoded.HardcodedDummyProductsServiceImpl
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
            return if (true) {
                HardcodedDummyProductsServiceImpl()
            } else {
                DummyProductsServiceImpl(
                    apiKey = Config::apiKey,
                    httpClient = HttpClient()
                )
            }
        }
    }
}
