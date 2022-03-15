package com.narcissus.marketplace.api.source.dummyproducts

object Endpoints {
    private fun endpoint(path: String) = "${Constants.BASE_URL}/$path"

    val PRODUCTS = endpoint("products")
    fun product(id: String) = "$PRODUCTS/$id"

    val PRODUCTS_TOP_SALES = endpoint("$PRODUCTS/topsales")
    val PRODUCTS_TOP_RATED = endpoint("$PRODUCTS/toprated")
    val PRODUCTS_RANDOM = endpoint("$PRODUCTS/random")

    val SEARCH = endpoint("$PRODUCTS/search")

    val DEPARTMENTS = endpoint("departments")

    val CHECKOUT = endpoint("actions/checkout")
}