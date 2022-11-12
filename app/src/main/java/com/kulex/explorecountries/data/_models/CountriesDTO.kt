package com.kulex.explorecountries.data._models

@kotlinx.serialization.Serializable
data class CountriesDTO(
//    val postalCode: PostalCode? = null,
    val name: Name? = null,
//    val timezones: List<String>? = null,
    val capital: List<String>? = null,
    val flags: Flags? = null,
    /*val region: String? = null,
    val currency: Map<String, Map<String, String>>? = null,
    val languages: Map<String, Map<String, String>>? = null,
    val car: Car? = null,
    val population: Int? = null,
    val area: Double? = null,*/
)

@kotlinx.serialization.Serializable
data class Country(
    val name: String,
    val capital: String,
    val flag: String
)


@kotlinx.serialization.Serializable
data class Name(
    val official: String? = null,
)

@kotlinx.serialization.Serializable
data class Flags(
    val png: String? = null,
    val svg: String? = null
)

@kotlinx.serialization.Serializable
data class PostalCode(
    val format: String? = null,
    val regex: String? = null
)

@kotlinx.serialization.Serializable
data class Car(
    val side: String? = null
)