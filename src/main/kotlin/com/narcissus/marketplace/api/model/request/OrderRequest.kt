package com.narcissus.marketplace.api.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderRequest(
    val id: String,
    @SerialName("order_items")
    val orderItems: List<OrderRequestItem>
)