package com.narcissus.marketplace.api.plugins

import com.narcissus.marketplace.api.di.ServiceLocator
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.io.File

fun Application.configureRouting() {
    val api = ServiceLocator.dummyProductsApiClient

    routing {
        get("departments") {
            val departmentsJsonFile = File("src/main/resources/departments.json")
            call.respondFile(departmentsJsonFile)
        }

        get("products/{id}") {
            val id = call.parameters["id"] ?: return@get
            val response = api.getProductDetails(id)
            call.respond(response)
        }

        get("products/random") {
            val response = api.getRandomProducts(8, 1)
            call.respond(response)
        }

        get("products/topsales") {
            val response = api.getTopSalesProducts(8, 1)
            call.respond(response)
        }

        get("products/toprated") {
            val response = api.getTopRatedProducts(8, 1)
            call.respond(response)
        }
    }
}