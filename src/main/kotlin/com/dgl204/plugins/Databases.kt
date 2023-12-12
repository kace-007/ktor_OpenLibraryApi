package com.dgl204.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Table


fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )

    // Create tables
    transaction {
        SchemaUtils.create(Usernames) // Add SavedCovers here
    }
}

object Usernames : Table() {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 255)
    override val primaryKey = PrimaryKey(id)
}
