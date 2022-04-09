package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EntitySimilarProducts(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EntitySimilarProducts>(SimilarProducts)

    var product by EntityProduct optionalReferencedOn SimilarProducts.product
    var similarProductId by SimilarProducts.similarProductId
}