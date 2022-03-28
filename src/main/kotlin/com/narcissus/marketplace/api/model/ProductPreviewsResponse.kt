package com.narcissus.marketplace.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductPreviewsResponse(
    @SerialName("data")
    val data: List<ProductPreview>
)