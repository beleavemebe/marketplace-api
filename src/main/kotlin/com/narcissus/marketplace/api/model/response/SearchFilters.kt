package com.narcissus.marketplace.api.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SearchFilters(
    val departmentValues: List<String>,
    val priceLowerBound: Int,
    val priceUpperBound: Int,
    val materialValues: List<String>,
    val colorValues: List<String>,
)
