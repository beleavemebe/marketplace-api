package com.narcissus.marketplace.api.data

import com.narcissus.marketplace.api.model.Review
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class EntityReview(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<EntityReview>(Reviews)

    var product by EntityProduct optionalReferencedOn Reviews.product
    var author by Reviews.author
    var details by Reviews.details
    var rating by Reviews.rating
    var avatar by Reviews.avatar

    fun toReview(): Review =
        Review(
            id.value.toString(),
            product?.id?.value.toString(),
            author,
            details,
            rating,
            avatar,
        )
}