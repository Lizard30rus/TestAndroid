package com.example.testandroid.data.remote.models

import java.util.*

data class CountryDetail(
    val country : String,
    val countryCode : String,
    val lat : String,
    val lon : String,
    val cases : String,
    val status : String,
    val date : Date,
)