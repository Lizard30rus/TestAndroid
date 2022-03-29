package com.example.testandroid.datasource


import com.example.testandroid.data.models.CountryDTI
import com.example.testandroid.data.models.CountryDetailDTI
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface WebDataSource{

    @GET ("countries")
    suspend fun updateCountries() : List<CountryDTI>

    @GET ("live/country/{countryName}/status/confirmed")
    suspend fun getCountryDetail(
        @Path ("countryName") countryName: String,
    firstDate: Date,
    lastDate : Date)
    : List<CountryDetailDTI>
}