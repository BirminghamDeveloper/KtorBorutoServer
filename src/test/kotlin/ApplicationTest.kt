package com.example

import com.example.plugins.module
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.server.engine.applicationEnvironment
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import models.ApiResponse
import org.koin.java.KoinJavaComponent.inject
import repos.HeroRepository
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    private val heroRepo: HeroRepository by inject(HeroRepository::class.java)

    @Test
    fun `access root endpoint, assert correct information`() = testApplication {
        application {
            module()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Test
    fun `access all heroes endpoint, assert correct information`() = testApplication {
        application { module() }

        val expected = ApiResponse(
            success = true,
            message = "Ok",
            prevPage = null,
            nextPage = 2,
            heroes = heroRepo.page1
        )

        val response = client.get("/boruto/heroes")
        assertEquals(HttpStatusCode.OK, response.status)

        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())

        println("EXPECTED: $expected")
        println("ACTUAL: $actual")

        assertEquals(expected, actual)
    }

    @Test
    fun `access all heroes endpoint, query second page, assert correct information`() = testApplication {
        application { module() }

        val expected = ApiResponse(
            success = true,
            message = "Ok",
            prevPage = null,
            nextPage = 2,
            heroes = heroRepo.page1
        )

        val response = client.get("/boruto/heroes?page=2")
        assertEquals(HttpStatusCode.OK, response.status)

        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())

        println("EXPECTED: $expected")
        println("ACTUAL: $actual")

        assertEquals(expected, actual)
    }
}
