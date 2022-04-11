package com.narcissus.marketplace.api.data

import com.narcissus.marketplace.api.model.Order
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class EntityOrder(id: EntityID<UUID>) : UUIDEntity(id) {
    fun toOrder(): Order {
        return Order(
            orderId = id.value.toString(),
            orderNumber = orderNumber,
            paymentStatus = paymentStatus,
            message = message,
            orderItems = items.map(EntityOrderItem::toOrderItem)
        )
    }

    companion object : UUIDEntityClass<EntityOrder>(Orders)

    var orderNumber by Orders.orderNumber
    var paymentStatus by Orders.paymentStatus
    var message by Orders.message
    val items by EntityOrderItem referrersOn OrderItems.order
}