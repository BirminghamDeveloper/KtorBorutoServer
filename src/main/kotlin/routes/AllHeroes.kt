package routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.*
import models.ApiResponse

// Check with the guys
fun Route.getAllHeroes() {
    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1

            require(page in 1..5)
            call.respond(message = page)
            // Continue processing if successful
        } catch (ex: NumberFormatException) {
            call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse(success = false, message = "Only Numbers Allowed here")
            )
        } catch (e: IllegalArgumentException){
            call.respond(
                HttpStatusCode.NotFound,
                ApiResponse(success = false, message = "Heroes Not Found.")
            )
        }
    }
}

/*
// bad move
fun Route.getAllHeroes(){
    get("/boruto/heroes"){
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
        } catch (ex: NumberFormatException){
            call.respond(
                message = ApiResponse(success = false, message = "Only Numbers Allowed here"),
                status = HttpStatusCode.BadRequest
            )
        }

    }
}
*/
