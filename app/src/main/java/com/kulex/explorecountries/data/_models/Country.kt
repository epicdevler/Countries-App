package com.kulex.explorecountries.data._models

@kotlinx.serialization.Serializable
data class Country(
    val name: String,
    val capital: String,
    val flag: String
)