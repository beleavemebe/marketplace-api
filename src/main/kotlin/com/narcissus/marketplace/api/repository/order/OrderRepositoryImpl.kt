package com.narcissus.marketplace.api.repository.order

import com.narcissus.marketplace.api.model.Order

class OrderRepositoryImpl : OrderRepository {
    override suspend fun insert(order: Order) {
        TODO("Not yet implemented")
    }

    override suspend fun getSize(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): Order {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: Order) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(item: Order) {
        TODO("Not yet implemented")
    }
}