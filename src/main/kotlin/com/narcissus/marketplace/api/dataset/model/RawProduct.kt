package com.narcissus.marketplace.api.dataset.model

data class RawProduct(
    val name: String,
    val productImg150: String,
    val productImg300: String,
    val productImg600: String,
    val departmentId: String,
    val departmentName: String,
    val type: String,
)
