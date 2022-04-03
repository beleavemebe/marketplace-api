package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.id.IntIdTable

object SalesInfo : IntIdTable() {
    val product = reference("product", Products)
    var prevPrice = integer("prev_price")
    var percentOff = integer("percent_off")
    var msRemaining = long("ms_remaining")
}
