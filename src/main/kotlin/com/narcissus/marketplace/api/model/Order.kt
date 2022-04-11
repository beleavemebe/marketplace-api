package com.narcissus.marketplace.api.model

import com.narcissus.marketplace.api.model.request.OrderItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Order(
    @SerialName("order_id")
    val orderId: String,
    @SerialName("order_number")
    val orderNumber: Int?,
    @SerialName("order_payment_status")
    val paymentStatus: String,
    @SerialName("message")
    val message: String,
    @SerialName("order_items")
    val orderItems: List<OrderItem> = emptyList(),
)
