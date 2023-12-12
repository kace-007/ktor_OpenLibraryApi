package com.dgl204

import com.dgl204.plugins.*
import io.ktor.server.application.*

import kotlinx.serialization.json.Json


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureTemplating()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
