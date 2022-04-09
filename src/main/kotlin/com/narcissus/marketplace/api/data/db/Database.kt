package com.narcissus.marketplace.api.data.db

import com.narcissus.marketplace.api.data.Products
import com.narcissus.marketplace.api.data.Reviews
import com.narcissus.marketplace.api.data.SalesInfo
import com.narcissus.marketplace.api.data.SimilarProducts
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.initDatabase() {
    val dbConfig = environment.config.config("database")
    val host = dbConfig.property("host").getString()
    val port = dbConfig.property("port").getString()
    val name = dbConfig.property("name").getString()
    val user = dbConfig.property("user").getString()
    val password = dbConfig.property("password").getString()

    Database.connect(
        url = "jdbc:postgresql://$host:$port/$name",
        driver = "org.postgresql.Driver",
        user = user,
        password = password,
    )

    transaction {
        val tables = arrayOf(Products, Reviews, SalesInfo, SimilarProducts)
        SchemaUtils.createMissingTablesAndColumns(*tables)
    }
}