package com.kulex.explorecountries.data.repo

import com.kulex.explorecountries.data.utils.CONSTANTS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInit {


    private operator fun invoke(): Retrofit = Retrofit.Builder()
        .baseUrl(CONSTANTS.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    fun init(): CountryRepo = invoke().create(CountryRepo::class.java)

}