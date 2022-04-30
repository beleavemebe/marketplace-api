package com.narcissus.marketplace.api.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ProductsAmountResponse(
    val productsAmount: Int,
)