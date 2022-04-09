package com.narcissus.marketplace.api.di

import com.narcissus.marketplace.api.repository.order.OrderRepositoryImpl
import com.narcissus.marketplace.api.repository.product.ProductRepositoryImpl
import com.narcissus.marketplace.api.service.checkout.CheckoutService

object ServiceLocator {
    val productRepository by lazy { ProductRepositoryImpl() }
    val orderRepository by lazy { OrderRepositoryImpl() }
    val checkoutService by lazy { CheckoutService(orderRepository) }
}
