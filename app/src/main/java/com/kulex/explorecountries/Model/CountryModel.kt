package com.kulex.explorecountries.Model

import android.graphics.Region

data class CountryModel(val name: String?,
                        val flag: String?,
                        val population: Long,
                        val region: Region?,
                        val capital: String?,

                        val languages: Map<String, String>? = null,

                        val independent: Boolean? = null,
                        val area: Double,
//                      val currencies: Currencies? = null,
                        
                        val timezones: List<String>,
//                        val subregion: String? = null,
//                        val postalCode: PostalCode? = null,
//                        val translations: Map<String, Translation>,
//                        val borders: List<String>? = null,
//                        val latlng: List<Double>,
//                        val landlocked: Boolean,
)
