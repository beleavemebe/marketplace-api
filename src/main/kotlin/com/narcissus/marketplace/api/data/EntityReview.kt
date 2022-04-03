package com.narcissus.marketplace.api.data

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class EntityReview(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<EntityReview>(Reviews)

    val product by EntityProduct referencedOn Reviews.product
    var author by Reviews.author
    var details by Reviews.details
    var rating by Reviews.rating
    var avatar by Reviews.avatar
}