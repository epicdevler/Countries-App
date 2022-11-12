package com.kulex.explorecountries.data.repo

import android.util.Log
import com.kulex.explorecountries.data.utils.CONSTANTS
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object CountryRepo {

    private const val TAG = "KTOR_TAG"
    private const val TIME_OUT = 60_000

    private val client = HttpClient(Android) {
        defaultRequest {
//            url(CONSTANTS.BASE_URL)
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(ResponseObserver) {
            onResponse {
                Log.e(TAG, "Response: $it")
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }

    }

    suspend fun get(): HttpResponse {
        return client.get("${CONSTANTS.BASE_URL}all")
    }

}

