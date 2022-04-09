package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object Reviews : UUIDTable() {
    val product = optReference("product", Products, onDelete = ReferenceOption.CASCADE)
    var author = varchar("author", length = 127)
    var details = text("details")
    var rating = integer("rating")
    var avatar = varchar("avatar", length = 127)
}
