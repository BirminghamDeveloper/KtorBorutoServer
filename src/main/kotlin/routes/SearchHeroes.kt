package routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import repos.HeroRepository

fun Route.searchHeroes() {
    val heroRepo: HeroRepository by application.inject()

    get("/boruto/heroes/search"){
        val name = call.request.queryParameters["name"]

        val apiReponse = heroRepo.searchHeroes(name = name)
        call.respond(
            message = apiReponse,
            status = HttpStatusCode.OK
        )
    }
}