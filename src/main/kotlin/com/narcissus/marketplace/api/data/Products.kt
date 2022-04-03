package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.id.UUIDTable

object Products : UUIDTable() {
    var productImg150 = varchar("product_img_150", length = 127)
    var productImg300 = varchar("product_img_300", length = 127)
    var productImg600 = varchar("product_img_600", length = 127)
    var name = varchar("name", length = 127)
    var price = integer("price")
    var saleInfo = reference("sale_info", SalesInfo)
    var type = varchar("type", length = 30)
    var departmentId = varchar("type", length = 30)
    var departmentName = varchar("type", length = 30)
    var inStock = integer("in_stock")
    var color = varchar("color", length = 15)
    var material = varchar("material", length = 30)
    var rating = integer("rating")
    var sales = integer("sales")
    var description = text("description")
    var reviews = reference("reviews", Reviews)
}
