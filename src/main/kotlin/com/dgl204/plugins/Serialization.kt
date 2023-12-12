package com.dgl204.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json



fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
                // Configure the Json instance to ignore unknown keys
                ignoreUnknownKeys = true
            }
        )
    }
}

