package com.narcissus.marketplace.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetails(
    @SerialName("_id")
    val id: String,

    @SerialName("product_image_lg")
    val productImg: String,

    @SerialName("product_name")
    val name: String,

    @SerialName("product_price")
    val price: Int,

    @SerialName("product_type")
    val type: String,

    @SerialName("product_department")
    val departmentName: String,

    @SerialName("product_stock")
    val stock: Int,

    @SerialName("product_color")
    val color: String,

    @SerialName("product_material")
    val material: String,

    @SerialName("product_ratings")
    val rating: Int,

    @SerialName("product_sales")
    val sales: Int,

    @SerialName("product_description")
    val description: String,

    @SerialName("product_reviews")
    val reviewsList: List<Review>,

    @SerialName("product_similar")
    val similarProductsList: List<SimilarProduct>,
)