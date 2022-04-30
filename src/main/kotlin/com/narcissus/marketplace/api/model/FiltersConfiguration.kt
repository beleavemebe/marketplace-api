package com.narcissus.marketplace.api.model

import kotlinx.serialization.Serializable

@Serializable
data class FiltersConfiguration(
    val sortBy: String,
    val department: String?,
    val priceLowerBound: Int = 0,
    val priceUpperBound: Int = Int.MAX_VALUE,
    val material: String?,
    val color: String?,
)