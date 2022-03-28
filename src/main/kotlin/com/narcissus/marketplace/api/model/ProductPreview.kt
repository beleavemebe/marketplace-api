package com.narcissus.marketplace.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductPreview(
    @SerialName("_id")
    val id: String,

    @SerialName("product_image_lg")
    val icon: String,

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
    val sales: Int
)