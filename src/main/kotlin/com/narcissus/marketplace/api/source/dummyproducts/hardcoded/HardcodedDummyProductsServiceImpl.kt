package com.narcissus.marketplace.api.source.dummyproducts.hardcoded

import com.narcissus.marketplace.api.model.*
import com.narcissus.marketplace.api.source.dummyproducts.DummyProductsService
import com.narcissus.marketplace.api.source.dummyproducts.hardcoded.FakeData.preview
import com.narcissus.marketplace.api.source.dummyproducts.hardcoded.FakeData.details
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HardcodedDummyProductsServiceImpl : DummyProductsService {
    private val previewsResponse = ProductPreviewsResponse(listOf(preview, preview, preview, preview, preview))
    private val detailsResponse = ProductDetailsResponse(details)

    override suspend fun getProducts(limit: Int, page: Int): String {
        return Json.encodeToString(previewsResponse)
    }

    override suspend fun searchProducts(query: String): String {
        return Json.encodeToString(previewsResponse)
    }

    override suspend fun getProductDetails(productId: String, similarities: String): String {
        return Json.encodeToString(detailsResponse)
    }

    override suspend fun getRandomProducts(limit: Int, page: Int): String {
        return Json.encodeToString(previewsResponse)
    }

    override suspend fun getTopRatedProducts(limit: Int, page: Int): String {
        return Json.encodeToString(previewsResponse)
    }

    override suspend fun getTopSalesProducts(limit: Int, page: Int): String {
        return Json.encodeToString(previewsResponse)
    }

    override suspend fun checkout(orderRequest: OrderRequest): String {
        return ""
    }
}