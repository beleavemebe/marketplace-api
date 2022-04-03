package com.narcissus.marketplace.api.model

import kotlinx.serialization.Serializable

@Serializable
data class SaleInfo(
    val prevPrice: Int,
    val percentOff: Int,
    val msRemaining: Long,
)
