package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.http.content.staticResources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import plugins.configureStatusPages
import routes.getAllHeroes
import routes.root
import routes.searchHeroes
import javax.naming.AuthenticationException


fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
        searchHeroes()
        configureStatusPages()

        /*get("/test2"){
            throw AuthenticationException()
        }*/

        staticResources("/images", "images")

        /*
        // Deprecated Way
        static("/images") {
            resources("images")
        }*/

    }
}
