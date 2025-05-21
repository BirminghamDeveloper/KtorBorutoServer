package routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.*
import models.ApiResponse
import org.koin.ktor.ext.inject
import repos.HeroRepository

// Check with the guys
fun Route.getAllHeroes() {
    val heroRepo: HeroRepository by application.inject()

    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1

            require(page in 1..5)
            val apiResponse = heroRepo.getAllHeroes(page = page)

            call.respond(message = apiResponse, status = HttpStatusCode.OK)

        // Continue processing if successful
        } catch (ex: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = "Only Numbers Allowed here"),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false, message = "Heroes Not Found."),
                status = HttpStatusCode.NotFound
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
