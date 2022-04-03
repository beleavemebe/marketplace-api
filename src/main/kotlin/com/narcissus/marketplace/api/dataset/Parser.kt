package com.narcissus.marketplace.api.dataset

import com.narcissus.marketplace.api.dataset.model.ProductImage
import com.narcissus.marketplace.api.dataset.model.ProductImageFiles
import com.narcissus.marketplace.api.dataset.model.dropNumbersAtTheEnd
import java.io.File

class Parser {
    fun parseImages(): List<ProductImage> {
        val allImages = gatherImages().filterNot { it.isDirectory }

        val groupedByProductName: List<ProductImageFiles> = allImages
            .groupBy { it.nameWithoutExtension.dropNumbersAtTheEnd() }
            .values
            .map {
                ProductImageFiles(
                    it[0],
                    it[1],
                    it[2],
                )
            }

        return groupedByProductName.map {
            val depName = it.departmentNameSnakeCase()
            val name = it.productNameSnakeCase()
            val img150 = it.file150.name
            val img300 = it.file300.name
            val img600 = it.file600.name
            ProductImage(
                depName,
                name,
                img150,
                img300,
                img600
            )
        }
    }

    private fun gatherImages(): List<File> {
        val imagesDir = File("src/main/resources/products")
        return imagesDir.walk().toList()
    }
}
