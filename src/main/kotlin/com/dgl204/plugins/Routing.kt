package com.dgl204.plugins

import com.dgl204.models.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*


import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.insert

import org.jetbrains.exposed.sql.SortOrder
import java.util.Base64



fun Application.configureRouting() {

    val client = HttpClient(CIO)

    routing {

        // Serving static files
        static("/static") {
            resources("static")
        }

        get("/") {
            val dataModel = mapOf("message" to "Enter your name")
            call.respond(FreeMarkerContent("index.ftl", dataModel))
        }

        post("/submit-name") {
            val parameters = call.receiveParameters()
            var username = parameters["username"] ?: "Guest"

            if (username.isNullOrEmpty()) {
                username = "Guest"
            }

            transaction {
                Usernames.insert {
                    it[Usernames.username] = username
                }
            }

            call.respondRedirect("/home")
        }

        get("/home") {

            // Extracting the 'username' from database
            val latestUsername = transaction {
                Usernames.selectAll().orderBy(Usernames.id to SortOrder.DESC).limit(1).map { it[Usernames.username] }
                    .singleOrNull() ?: "Guest"
            }

            // Creating a data model that includes the 'username'
            val dataModel = mapOf(
                "message" to "Search for your book here",
                "username" to latestUsername
            )

            call.respond(FreeMarkerContent("home.ftl", dataModel))
        }

        post("/submit") {
            val parameters = call.receiveParameters()
            val title = parameters["title"]?.replace(" ", "+")

            if (title.isNullOrEmpty()) {

                // Extracting the 'username' from database
                val latestUsername = transaction {
                    Usernames.selectAll().orderBy(Usernames.id to SortOrder.DESC).limit(1)
                        .map { it[Usernames.username] }.singleOrNull() ?: "Guest"
                }

                // Creating a data model that includes the 'username'
                val dataModel = mapOf(
                    "message" to "Search cannot be empty",
                    "username" to latestUsername
                )

                call.respond(FreeMarkerContent("home.ftl", dataModel))

            } else {

                call.respondRedirect("/books/$title")

            }

        }

        get("/books/{isbn}") {
            val isbn = call.parameters["isbn"]
            val response = CoroutineScope(Dispatchers.IO).async {
                client.get("https://openlibrary.org/isbn/$isbn.json")
            }.await()

            val jsonResponse: String = response.bodyAsText()

            // Deserialize the JSON response into a BookResponse object

            val json = Json { ignoreUnknownKeys = true } // Set to ignore unknown keys
            val bookResponse = json.decodeFromString<BookResponse>(jsonResponse)


            // Create the data model using the createBookDataModel function
            val bookDataModel = createBookDataModel(bookResponse)

            // Combine the book data model with the jsonResponse
            val dataModel = mapOf(
                "jsonResponse" to jsonResponse,
                "bookData" to bookDataModel
            )

            call.respond(FreeMarkerContent("bookresult.ftl", dataModel))
        }


        get("/cover/{query}") {
            val query = call.parameters["query"] ?: return@get call.respondText(
                "Missing or malformed query",
                status = HttpStatusCode.BadRequest
            )

            // Constructing the URL for the Open Library Covers API
            val url = "https://covers.openlibrary.org/b/isbn/$query-L.jpg"

            val response: HttpResponse = client.get(url)
            if (response.status == HttpStatusCode.OK) {
                val bytes = response.readBytes()
                val base64Image = Base64.getEncoder().encodeToString(bytes)

                // Pass the image data to the FreeMarker template
                call.respond(
                    FreeMarkerContent(
                        "cover.ftl",
                        mapOf("image" to base64Image),
                        ""
                    )
                )
            } else {
                call.respondText(
                    "Image not found",
                    status = HttpStatusCode.NotFound
                )
            }
        }


        post("/submit-cover") {
            val parameters = call.receiveParameters()
            val isbn = parameters["cover"]

            if (isbn.isNullOrEmpty()) {

                // Extracting the 'username' from database
                val latestUsername = transaction {
                    Usernames.selectAll().orderBy(Usernames.id to SortOrder.DESC).limit(1)
                        .map { it[Usernames.username] }.singleOrNull() ?: "Guest"
                }

                // Creating a data model that includes the 'username'
                val dataModel = mapOf(
                    "message" to "Search cannot be empty",
                    "username" to latestUsername
                )

                call.respond(FreeMarkerContent("home.ftl", dataModel))

            } else {

                call.respondRedirect("/cover/$isbn")

            }

            }

        post("/submit-author") {
            val parameters = call.receiveParameters()
            val author = parameters["author"]

            if (author.isNullOrEmpty()) {

                // Extracting the 'username' from database
                val latestUsername = transaction {
                    Usernames.selectAll().orderBy(Usernames.id to SortOrder.DESC).limit(1)
                        .map { it[Usernames.username] }.singleOrNull() ?: "Guest"
                }

                // Creating a data model that includes the 'username'
                val dataModel = mapOf(
                    "message" to "Search cannot be empty",
                    "username" to latestUsername
                )

                call.respond(FreeMarkerContent("home.ftl", dataModel))

            } else {

                call.respondRedirect("/author/$author")

            }


        }

        get("/author/{query}") {
            val query = call.parameters["query"]?.replace(" ", "+")
            if (query == null) {
                call.respond(HttpStatusCode.BadRequest, "Query parameter is missing")
                return@get
            }

            val response: HttpResponse = CoroutineScope(Dispatchers.IO).async {
                client.get("https://openlibrary.org/search/authors.json?q=$query")
            }.await()

            if (response.status == HttpStatusCode.NotFound) {
                call.respondText(
                    "Author not found",
                    status = HttpStatusCode.NotFound
                )
            } else {
                val jsonResponse: String = response.bodyAsText()

                // Deserialize the JSON response into an AuthorResponse object
                val authorResponse = Json.decodeFromString<AuthorResponse>(jsonResponse)

// Create the data model using the createAuthorDataModel function
                val authorDataModel = createAuthorDataModel(authorResponse)

// Combine the author data model with the jsonResponse
                val dataModel = mapOf(
                    "jsonResponse" to jsonResponse,
                    "authorData" to authorDataModel
                )

                call.respond(FreeMarkerContent("author.ftl", dataModel))

            }
        }


    }
}
