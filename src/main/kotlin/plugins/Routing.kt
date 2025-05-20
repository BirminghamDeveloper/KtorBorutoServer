package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import routes.getAllHeroes
import routes.root

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
    }
}
