package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.id.UUIDTable

object OrderItems : UUIDTable() {
    var product = reference("product", Products)
    var order = reference("order", Orders)
    var amount = integer("amount")
}