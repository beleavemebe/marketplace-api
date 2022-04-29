package com.narcissus.marketplace.api.model.response

data class SearchFiltersResponse(
    val departmentValues: List<String>,
    val priceLowerBound: Int,
    val priceUpperBound: Int,
    val materialValues: List<String>,
    val colorValues: List<String>,
    val productsAmount: Int,
)

data class FiltersConfiguration(
    val sortBy: String,
    val department: String?,
    val priceLowerBound: Int = 0,
    val priceUpperBound: Int = Int.MAX_VALUE,
    val material: String?,
    val color: String?,
)

data class SearchFiltersRequest(
    val searchQuery: String,
)

