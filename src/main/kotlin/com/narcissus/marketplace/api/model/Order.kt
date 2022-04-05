package com.narcissus.marketplace.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    @SerialName("order_id")
    val orderId: String,
    @SerialName("order_number")
    val orderNumber: Int?,
    @SerialName("order_payment_status")
    val paymentStatus: String,
    @SerialName("message")
    val message: String
)
