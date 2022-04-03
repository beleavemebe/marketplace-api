package com.narcissus.marketplace.api.repository.order

import com.narcissus.marketplace.api.model.Order

interface OrderRepository {
    suspend fun insert(order: Order)
    suspend fun getSize(): Int
    suspend fun getAll(): List<Order>
    suspend fun getById(id: Int): Order
    suspend fun update(item: Order)
    suspend fun delete(item: Order)
}