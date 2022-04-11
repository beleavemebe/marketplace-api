package com.narcissus.marketplace.api

import com.narcissus.marketplace.api.data.db.initDatabase
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.narcissus.marketplace.api.route.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.*
import io.ktor.serialization.kotlinx.json.*


fun main() {
    embeddedServer(
        Netty, environment = applicationEngineEnvironment {
            config = HoconApplicationConfig(ConfigFactory.load())

            connector {
                port = System.getenv("PORT")?.toInt() ?: 8080
            }

            module {
                install(ContentNegotiation) {
                    json()
                }

                initDatabase()
                configureRouting()
            }
        }
    ).start(wait = true)
}
