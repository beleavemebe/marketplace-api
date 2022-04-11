package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable

object Orders : UUIDTable() {
    val orderNumber = integer("order_number")
    val paymentStatus = varchar("order_status", 63)
    val message = text("message")
}