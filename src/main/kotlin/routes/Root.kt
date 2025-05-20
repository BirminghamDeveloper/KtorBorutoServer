package routes

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.root() {
    get("/") {
        call.respond(
            message = "Welcome to the Boruto Api",
            status = HttpStatusCode.OK
        )
    }
}