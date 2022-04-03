package com.narcissus.marketplace.api.repository.product.dummyproductsapi

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun <reified T> String.decode() = Json.decodeFromString<T>(this)