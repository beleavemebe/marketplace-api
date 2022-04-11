package com.narcissus.marketplace.api.repository.order

import com.narcissus.marketplace.api.data.EntityOrder
import com.narcissus.marketplace.api.data.EntityOrderItem
import com.narcissus.marketplace.api.data.EntityProduct
import com.narcissus.marketplace.api.model.Order
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.IllegalArgumentException
import java.util.*

class OrderRepositoryImpl : OrderRepository {
    override suspend fun insert(order: Order) = transaction {
        if (order.orderNumber == null) return@transaction

        val orderEntity = EntityOrder.new(UUID.fromString(order.orderId)) {
            orderNumber = order.orderNumber
            paymentStatus = order.paymentStatus
            message = order.message
        }

        order.orderItems.forEach { orderItem ->
            EntityOrderItem.new {
                this.order = orderEntity
                amount = orderItem.amount
                product = EntityProduct.findById(UUID.fromString(orderItem.id))
                    ?: throw IllegalArgumentException("Product by id ${orderItem.id} does not exist in the database")
            }
        }
    }

    override suspend fun getSize(): Int = transaction {
        EntityOrder.count().toInt()
    }

    override suspend fun getAll(): List<Order> = transaction {
        EntityOrder.all()
            .map(EntityOrder::toOrder)
    }
}