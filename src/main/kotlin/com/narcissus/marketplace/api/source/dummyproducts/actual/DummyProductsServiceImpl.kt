package com.narcissus.marketplace.api.source.dummyproducts.actual

import com.narcissus.marketplace.api.model.OrderRequest
import com.narcissus.marketplace.api.source.dummyproducts.DummyProductsService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DummyProductsServiceImpl(
    private val apiKey: () -> String,
    private val httpClient: HttpClient,
) : DummyProductsService {
    override suspend fun getProducts(limit: Int, page: Int): String =
        httpClient.get(Endpoints.PRODUCTS) {
            parameter("limit", limit)
            parameter("page", page)
            parameter("apikey", apiKey())
        }.body()

    override suspend fun searchProducts(query: String): String =
        httpClient.get(Endpoints.SEARCH) {
            parameter("term", query)
            parameter("apikey", apiKey())
        }.body()

    override suspend fun getProductDetails(productId: String, similarities: String): String =
        httpClient.get(Endpoints.product(productId)) {
            parameter("similarities", similarities)
            parameter("apikey", apiKey())
        }.body()

    override suspend fun getRandomProducts(limit: Int, page: Int): String =
        httpClient.get(Endpoints.PRODUCTS_RANDOM) {
            parameter("limit", limit)
            parameter("page", page)
            parameter("apikey", apiKey())
        }.body()

    override suspend fun getTopRatedProducts(limit: Int, page: Int): String =
        httpClient.get(Endpoints.PRODUCTS_TOP_RATED) {
            parameter("limit", limit)
            parameter("page", page)
            parameter("apikey", apiKey())
        }.body()

    override suspend fun getTopSalesProducts(limit: Int, page: Int): String =
        httpClient.get(Endpoints.PRODUCTS_TOP_SALES) {
            parameter("limit", limit)
            parameter("page", page)
            parameter("apikey", apiKey())
        }.body()

    override suspend fun checkout(orderRequest: OrderRequest): String =
        httpClient.post(Endpoints.CHECKOUT) {
            setBody(Json.encodeToString(orderRequest))
            parameter("apikey", apiKey())
        }.body()
}