package com.narcissus.marketplace.api.source.dummyproducts

object Endpoints {
    private fun endpoint(path: String) = "${Constants.BASE_URL}/$path"

    val PRODUCTS = endpoint("products")
    fun product(id: String) = "$PRODUCTS/$id"

    val PRODUCTS_TOP_SALES = "$PRODUCTS/topsales"
    val PRODUCTS_TOP_RATED = "$PRODUCTS/toprated"
    val PRODUCTS_RANDOM = "$PRODUCTS/random"
    val SEARCH = "$PRODUCTS/search"

    val CHECKOUT = endpoint("actions/checkout")
}