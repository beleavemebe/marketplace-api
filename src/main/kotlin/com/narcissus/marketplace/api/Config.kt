package com.narcissus.marketplace.api

import io.ktor.server.application.*

object Config {
    lateinit var keySet: List<String>
    private var currKeyIndex = 0

    val apiKey get() = keySet[currKeyIndex++ % keySet.size]
}

fun Application.initConfig() {
    Config.keySet = environment.config.property("keys.API_KEYS").getList()
}
