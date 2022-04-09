package com.narcissus.marketplace.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val name: String,
    val productImg150: String,
    val productImg300: String,
    val productImg600: String,
    val departmentId: String,
    val departmentName: String,
    val type: String,
    val price: Int,
    val saleInfo: SaleInfo?,
    val inStock: Int,
    val color: String,
    val material: String,
    val rating: Int,
    val sales: Int,
    val description: String,
    val reviews: List<Review>,
    val similarProducts: List<String>,
) {
    fun toProductDetails(): ProductDetails =
        ProductDetails(
            id,
            productImg600,
            name,
            price,
            type,
            departmentName,
            inStock,
            color,
            material,
            rating,
            sales,
            description,
            reviews,
            similarProducts
        )

    fun toProductPreview(): ProductPreview =
        ProductPreview(
            id,
            productImg300,
            name,
            price,
            type,
            departmentName,
            inStock,
            color,
            material,
            rating,
            sales,
        )

    fun toSimilarProduct(): SimilarProduct =
        SimilarProduct(
            id,
            productImg300,
            name,
            price,
            type,
            departmentName,
            inStock,
            rating
        )
}
