package com.narcissus.marketplace.api.data

import com.narcissus.marketplace.api.model.Product
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


class EntityProduct(id: EntityID<UUID>) : UUIDEntity(id) {
    fun toProduct(): Product =
        Product(
            id.value.toString(),
            name,
            productImg150,
            productImg300,
            productImg600,
            departmentId,
            departmentName,
            type,
            price,
            saleInfo?.toSaleInfo(),
            inStock,
            color,
            material,
            rating,
            sales,
            description,
            reviews.map(EntityReview::toReview),
            similarProducts.map(EntitySimilarProducts::similarProductId),
        )

    companion object : UUIDEntityClass<EntityProduct>(Products)

    var productImg150 by Products.productImg150
    var productImg300 by Products.productImg300
    var productImg600 by Products.productImg600
    var name by Products.name
    var price by Products.price
    var type by Products.type
    var departmentId by Products.departmentId
    var departmentName by Products.departmentName
    var inStock by Products.inStock
    var color by Products.color
    var material by Products.material
    var rating by Products.rating
    var sales by Products.sales
    var description by Products.description
    val saleInfo by EntitySaleInfo optionalReferencedOn Products.saleInfo
    val reviews by EntityReview optionalReferrersOn Reviews.product
    val similarProducts by EntitySimilarProducts optionalReferrersOn SimilarProducts.product
}