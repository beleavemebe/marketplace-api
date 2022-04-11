package com.narcissus.marketplace.api.repository.order

import com.narcissus.marketplace.api.model.Order

interface OrderRepository {
    suspend fun insert(order: Order)
    suspend fun getSize(): Int
    suspend fun getAll(): List<Order>
}