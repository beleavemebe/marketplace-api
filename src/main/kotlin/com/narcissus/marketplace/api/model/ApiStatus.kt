package com.narcissus.marketplace.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiStatus(
    @SerialName("is_available")
    val isAvailable: Boolean
)