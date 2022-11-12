package com.kulex.explorecountries.data.repo

import android.util.Log
import com.kulex.explorecountries.data._models.CountriesDTO
import com.kulex.explorecountries.data._models.Country
import com.kulex.explorecountries.data.utils.Response
import io.ktor.client.call.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCountryUseCase {

    private val repo = CountryRepo

    companion object {
        private const val TAG = "GET_COUNTRY_USE_CASE"
    }

    suspend fun get(): Response<List<Country>> = withContext(Dispatchers.IO) {
        try {
            val response = repo.get().body<List<CountriesDTO>>()
            val transformedData = response.map {
                Country(
                    name = it.name?.official!!,
                    capital = it.capital?.get(0) ?: "",
                    flag = it.flags?.svg!!
                )
            }
            Log.e(TAG, "get: $transformedData")
            Response.Success(data = transformedData, msg = "Success")
        } catch (e: Exception) {
            Log.e(TAG, "get Error Response:", e)
            Response.Error(msg = e.message)
        }
    }

}