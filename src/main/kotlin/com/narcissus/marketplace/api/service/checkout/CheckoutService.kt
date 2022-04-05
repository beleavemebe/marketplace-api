package com.narcissus.marketplace.api.service.checkout

import com.narcissus.marketplace.api.model.Order
import com.narcissus.marketplace.api.model.request.OrderRequest
import com.narcissus.marketplace.api.repository.order.OrderRepository
import kotlinx.coroutines.delay

class CheckoutService(
    private val orderRepository: OrderRepository,
) {
    suspend fun checkout(orderRequest: OrderRequest): Order {
        delay(4000L)
        return Order(
            orderId = orderRequest.id,
            orderNumber = null,
            paymentStatus = "CANCELED",
            message = "Transaction failed."
        )
    }
}
