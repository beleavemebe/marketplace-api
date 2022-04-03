package com.narcissus.marketplace.api.service.checkout

import com.narcissus.marketplace.api.repository.order.OrderRepository

interface CheckoutDeps {
    val orderRepository: OrderRepository
}