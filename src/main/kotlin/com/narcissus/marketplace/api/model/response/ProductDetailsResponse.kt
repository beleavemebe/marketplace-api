package com.narcissus.marketplace.api.model.response

import com.narcissus.marketplace.api.model.ProductDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsResponse(
    @SerialName("data")
    val data: ProductDetails
)

fun ProductDetails.wrapToResponse(): ProductDetailsResponse {
    return ProductDetailsResponse(this)
}
