package com.narcissus.marketplace.api.dataset

import com.narcissus.marketplace.api.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ProductsDataset {
    suspend fun createDataset(): List<Product> {
        val generator = DatasetGenerator()
        return withContext(Dispatchers.IO) { generator.generate() }
    }
}