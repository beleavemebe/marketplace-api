package com.narcissus.marketplace.api.data

import com.narcissus.marketplace.api.model.SaleInfo
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EntitySaleInfo(id: EntityID<Int>) : IntEntity(id) {
    fun toSaleInfo(): SaleInfo {
        return SaleInfo(
            prevPrice,
            percentOff,
            msRemaining
        )
    }

    companion object : IntEntityClass<EntitySaleInfo>(SalesInfo)

    val product by EntityProduct referencedOn SalesInfo.product
    var prevPrice by SalesInfo.prevPrice
    var percentOff by SalesInfo.percentOff
    var msRemaining by SalesInfo.msRemaining
}
