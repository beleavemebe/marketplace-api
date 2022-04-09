package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.id.UUIDTable

object Reviews : UUIDTable() {
    val product = optReference("product", Products)
    var author = varchar("author", length = 127)
    var details = text("details")
    var rating = integer("rating")
    var avatar = varchar("avatar", length = 127)
}
