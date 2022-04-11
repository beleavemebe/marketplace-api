package com.narcissus.marketplace.api.data

import com.narcissus.marketplace.api.model.request.OrderItem
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class EntityOrderItem(id: EntityID<UUID>) : UUIDEntity(id) {
    fun toOrderItem(): OrderItem {
        return OrderItem(
            id = id.value.toString(),
            amount = amount,
        )
    }

    companion object : UUIDEntityClass<EntityOrderItem>(OrderItems)

    var order by EntityOrder referencedOn OrderItems.order
    var product by EntityProduct referencedOn OrderItems.product
    var amount by OrderItems.amount
}