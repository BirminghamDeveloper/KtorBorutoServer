package plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.* // ✅ Import the StatusPages plugin
import io.ktor.server.response.* // ✅ Import respond()


fun Application.configureStatusPages(){
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(HttpStatusCode.NotFound, "Page not Found")
        }
    }

    /*install(StatusPages){
        status(HttpStatusCode.NotFound){
            call.respond(
                message = "Page not Found",
                status= HttpStatusCode.NotFound
            )
        }
        exception<AuthenticationException> {
            call.respond(HttpStatusCode.OK, "We caught an Exception!")

        }
    }*/
}