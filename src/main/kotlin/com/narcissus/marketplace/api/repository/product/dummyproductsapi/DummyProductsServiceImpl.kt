package com.narcissus.marketplace.api.repository.product.dummyproductsapi

import com.narcissus.marketplace.api.repository.product.RemoteProductRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class DummyProductsServiceImpl(
    private val apiKey: () -> String,
    private val httpClient: HttpClient,
) : RemoteProductRepository {

    override suspend fun searchProducts(query: String, page: Int, perPage: Int): String =
        httpClient.get(Endpoints.SEARCH) {
            parameter("term", query)
            parameter("apikey", apiKey())
        }.body()

    override suspend fun searchProductsTopRated(query: String, page: Int, perPage: Int): String =
        searchProducts(query, page, perPage)

    override suspend fun searchProductsTopSales(query: String, page: Int, perPage: Int): String =
        searchProducts(query, page, perPage)

}