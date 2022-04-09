package com.narcissus.marketplace.api.dataset

import com.narcissus.marketplace.api.model.Product
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue


internal class ProductsDatasetTest {
    private val products = mutableListOf<Product>()

    @Before
    fun setupProducts() = runBlocking {
        products.clear()
        products += ProductsDataset.createDataset()
    }

    @Test
    fun `all products should have unique ids`() {
        val size = products.size
        val distinctSize = products.distinctBy { it.id }.size
        assertTrue { size == distinctSize }
    }
}