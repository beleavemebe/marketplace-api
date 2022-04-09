package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.id.IntIdTable

object SimilarProducts : IntIdTable() {
    val product = optReference("product", Products)
    val similarProductId = varchar("similar", 63)
}