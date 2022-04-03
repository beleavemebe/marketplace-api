package com.narcissus.marketplace.api.dataset.model

import java.io.File

data class ProductImageFiles(
    val file150: File,
    val file300: File,
    val file600: File,
) {
    fun productNameSnakeCase() = file150.nameWithoutExtension.dropNumbersAtTheEnd()
    fun departmentNameSnakeCase() = file150.parentFile.name
}

fun String.dropNumbersAtTheEnd(): String {
    return split("_").dropLast(1).joinToString("_")
}
