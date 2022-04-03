package com.narcissus.marketplace.api.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderRequestItem(
    @SerialName("product_id")
    val id: String,

    @SerialName("product_amount")
    val amount: Int,
)