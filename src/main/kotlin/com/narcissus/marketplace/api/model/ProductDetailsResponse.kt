package com.narcissus.marketplace.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsResponse(
    @SerialName("data")
    val data: ProductDetails
)