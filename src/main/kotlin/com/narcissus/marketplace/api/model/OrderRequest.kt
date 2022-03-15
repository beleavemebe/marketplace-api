package com.narcissus.marketplace.api.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderRequest(
    val items: List<OrderRequestItem>
)