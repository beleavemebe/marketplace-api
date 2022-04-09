package com.narcissus.marketplace.api.route

import com.narcissus.marketplace.api.dataset.ProductsDataset
import com.narcissus.marketplace.api.di.ServiceLocator
import com.narcissus.marketplace.api.model.ApiStatus
import com.narcissus.marketplace.api.model.Product
import com.narcissus.marketplace.api.model.request.OrderRequest
import com.narcissus.marketplace.api.model.response.wrapToResponse
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun Application.configureRouting() {
    val productRepository = ServiceLocator.productRepository
    val checkoutService = ServiceLocator.checkoutService

    routing {
        get("departments") {
            val departmentsJsonFile = File("src/main/resources/departments.json")
            call.respondFile(departmentsJsonFile)
        }

        get("status") {
            val status = ApiStatus(isAvailable = true)
            call.respond(Json.encodeToString(status))
        }

        get("products/{id}") {
            val id = call.parameters["id"] ?: return@get

            val response = productRepository
                .getProductDetails(id)
                .wrapToResponse()

            call.respond(response)
        }

        get("products") {
            val limit = call.parameters["limit"]?.toInt() ?: 10
            val page = call.parameters["page"]?.toInt() ?: 1

            val response = productRepository
                .getProducts(limit, page)
                .wrapToResponse()

            call.respond(response)
        }

        get("products/search") {
            val query = call.parameters["term"] ?: ""
            val limit = call.parameters["limit"]?.toInt() ?: 10
            val page = call.parameters["page"]?.toInt() ?: 1

            val response = productRepository
                .searchProducts(query, limit, page)
                .wrapToResponse()

            call.respond(response)
        }

        get("products/random") {
            val limit = call.parameters["limit"]?.toInt() ?: 10
            val page = call.parameters["page"]?.toInt() ?: 1

            val response = productRepository
                .getRandomProducts(limit, page)
                .wrapToResponse()

            call.respond(response)
        }

        get("products/topsales") {
            val limit = call.parameters["limit"]?.toInt() ?: 10
            val page = call.parameters["page"]?.toInt() ?: 1

            val response = productRepository
                .getTopSalesProducts(limit, page)
                .wrapToResponse()

            call.respond(response)
        }

        get("products/toprated") {
            val limit = call.parameters["limit"]?.toInt() ?: 10
            val page = call.parameters["page"]?.toInt() ?: 1

            val response = productRepository
                .getTopRatedProducts(limit, page)
                .wrapToResponse()

            call.respond(response)
        }

        get("products/similar/{id}") {
            val id = call.parameters["id"]
                ?: throw BadRequestException("Missing 'id' parameter")

            val response = productRepository
                .getSimilarProducts(id)
                .map(Product::toProductPreview)
                .wrapToResponse()

            call.respond(response)
        }

        get("pic/{department}/{image}") {
            val department = call.parameters["department"]
                ?: throw BadRequestException("Missing 'department' parameter")
            val image = call.parameters["image"]
                ?: throw BadRequestException("Missing 'image' parameter")
            val imageFile = File("src/main/resources/products/$department/$image")
            call.respondFile(imageFile)
        }

        get("actions/refresh-dataset") {
            val dataset = ProductsDataset.createDataset()
            productRepository.deleteAll()
            productRepository.insertAll(dataset)
            call.respondText("Success")
        }

        post("actions/checkout") {
            val orderRequest = call.receiveOrNull<OrderRequest>()
                ?: throw BadRequestException("Could not deserialize order request")
            val response = checkoutService.checkout(orderRequest)
            call.respond(response)
        }
    }
}