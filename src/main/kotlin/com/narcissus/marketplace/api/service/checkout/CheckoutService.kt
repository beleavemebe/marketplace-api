package com.narcissus.marketplace.api.service.checkout

import com.narcissus.marketplace.api.model.Order
import com.narcissus.marketplace.api.model.request.OrderRequest
import com.narcissus.marketplace.api.repository.order.OrderRepository
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.random.nextInt

class CheckoutService(
    private val orderRepository: OrderRepository,
) {
    suspend fun checkout(orderRequest: OrderRequest): Order {
        delay(Random.Default.nextInt(2000..4000).toLong())
        return if (Random.Default.nextInt(0..100) in 0..65) {
            val order = Order(
                orderId = orderRequest.id,
                orderNumber = orderRepository.getSize() + 1,
                orderItems = orderRequest.orderItems,
                paymentStatus = "paid",
                message = "Transaction successful.",
            )
            orderRepository.insert(order)
            order
        } else {
            Order(
                orderId = orderRequest.id,
                orderNumber = null,
                paymentStatus = "canceled",
                message = "Transaction failed."
            )
        }
    }
}
