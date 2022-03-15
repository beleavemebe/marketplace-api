package com.narcissus.marketplace.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderRequestItem(
    @SerialName("_id") val id: String,
    @SerialName("product_quantity") val quantity: Int,
)