package com.narcissus.marketplace.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiStatus(
    val isAvailable: Boolean
)