package com.narcissus.marketplace.api.di

import com.narcissus.marketplace.api.repository.order.OrderRepository
import com.narcissus.marketplace.api.repository.order.OrderRepositoryImpl
import com.narcissus.marketplace.api.repository.product.ProductRepository
import com.narcissus.marketplace.api.repository.product.ProductRepositoryImpl
import com.narcissus.marketplace.api.service.checkout.CheckoutService

object ServiceLocator {
    val productRepository: ProductRepository by lazy { ProductRepositoryImpl() }
    val orderRepository: OrderRepository by lazy { OrderRepositoryImpl() }
    val checkoutService by lazy { CheckoutService(orderRepository) }
}
