package com.narcissus.marketplace.api.repository.product.dummyproductsapi

import com.narcissus.marketplace.api.model.Product
import com.narcissus.marketplace.api.repository.product.ProductRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class DummyProductsServiceImpl(
    private val apiKey: () -> String,
    private val httpClient: HttpClient,
) : ProductRepository {
    override suspend fun getProducts(limit: Int, page: Int): String =
        httpClient.get(Endpoints.PRODUCTS) {
            parameter("limit", limit)
            parameter("page", page)
            parameter("apikey", apiKey())
        }.body()

    override fun insertAll(products: List<Product>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

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

    override suspend fun getProductDetails(productId: String, similarities: String): String =
        httpClient.get(Endpoints.product(productId)) {
            parameter("similarities", similarities)
            parameter("apikey", apiKey())
        }.body()

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