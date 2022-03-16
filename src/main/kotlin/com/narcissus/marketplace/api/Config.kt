package com.narcissus.marketplace.api

import io.ktor.server.application.*

object Config {
    lateinit var apiKeys: List<String>
    private var currKeyIndex = 0

    val apiKey get() = apiKeys[currKeyIndex++ % apiKeys.size]
}

fun Application.initConfig() {
    Config.apiKeys = Array(3) { i ->
        environment.config.property("keys.API_KEY_${i + 1}").getString()
    }.toList().shuffled()
}
