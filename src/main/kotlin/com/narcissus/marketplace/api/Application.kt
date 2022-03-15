package com.narcissus.marketplace.api

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.narcissus.marketplace.api.plugins.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

fun main() {
    embeddedServer(
        Netty, environment = applicationEngineEnvironment {
            config = HoconApplicationConfig(ConfigFactory.load())

            connector {
                port = 8080
                host = "127.0.0.1"
            }

            module {
                initConfig()
                configureRouting()
            }
        }
    ).start(wait = true)
}
