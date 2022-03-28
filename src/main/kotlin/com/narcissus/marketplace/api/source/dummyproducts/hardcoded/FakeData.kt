package com.narcissus.marketplace.api.source.dummyproducts.hardcoded

import com.narcissus.marketplace.api.model.ProductDetails
import com.narcissus.marketplace.api.model.ProductPreview
import com.narcissus.marketplace.api.model.Review

object FakeData {
    val review = Review(
        "1",
        "1",
        "Joe Ordinary",
        "Sample review",
        4,
        "https://miro.medium.com/fit/c/176/176/1*pX7p3ApThISh42UA-JfF7A.png"
    )

    val details = ProductDetails(
        "1",
        "https://github.com/rookiemonkey/dummy-products-api/blob/master/products/gadgets/basicphone_600.png?raw=true",
        "Nokia 3310",
        300,
        "Mobile Phone",
        "gadgets",
        120,
        "Silver",
        "Plastic",
        4,
        213,
        "This is Nokia",
        listOf(
            review,
            Review("1", "1", "Joe Ordinary", "Sample review 1", 3, "https://miro.medium.com/fit/c/176/176/1*pX7p3ApThISh42UA-JfF7A.png"),
            Review("2", "1", "Joe Ordinary", "Sample review 2", 5, "https://miro.medium.com/fit/c/176/176/1*pX7p3ApThISh42UA-JfF7A.png"),
            Review("3", "1", "Joe Ordinary", "Sample review 3", 4, "https://miro.medium.com/fit/c/176/176/1*pX7p3ApThISh42UA-JfF7A.png"),
        ),
        emptyList()
    )
    
    val preview = details.toProductPreview()
}

private fun ProductDetails.toProductPreview(): ProductPreview {
    return ProductPreview(
        id, productImg, name, price, type, departmentName, stock, color, material, rating, sales
    )
}
