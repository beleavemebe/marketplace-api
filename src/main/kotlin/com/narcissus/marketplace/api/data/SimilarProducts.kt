package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object SimilarProducts : IntIdTable() {
    val product = optReference("product", Products, onDelete = ReferenceOption.CASCADE)
    val similarProductId = varchar("similar", 63)
}