package com.narcissus.marketplace.api.model.response

import com.narcissus.marketplace.api.model.ProductPreview
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductPreviewsResponse(
    @SerialName("data")
    val data: List<ProductPreview>
)

fun List<ProductPreview>.wrapToResponse(): ProductPreviewsResponse {
    return ProductPreviewsResponse(this)
}
