package com.narcissus.marketplace.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("_id")
    val reviewId: String,

    @SerialName("review_productid")
    val productId: String,

    @SerialName("review_name")
    val author: String,

    @SerialName("review_details")
    val details: String,

    @SerialName("review_rating")
    val rating: Int,

    @SerialName("review_avatar")
    val reviewAuthorIcon: String
)