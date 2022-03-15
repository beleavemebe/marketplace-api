package com.narcissus.marketplace.api.di

import com.narcissus.marketplace.api.source.dummyproducts.DummyProductsService

object ServiceLocator {
    val dummyProductsApiClient by lazy { DummyProductsService.newInstance() }
}
