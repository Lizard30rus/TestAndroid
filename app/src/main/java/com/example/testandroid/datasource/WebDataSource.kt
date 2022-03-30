package com.example.testandroid.datasource


import com.example.testandroid.data.models.CountryDTI
import com.example.testandroid.data.models.CountryDetailDTI
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface WebDataSource{

    @GET ("countries")
    suspend fun updateCountries() : List<CountryDTI>

    @GET ("country/{countryName}from={firstDate}T00:00:00Z&to={lastDate}T00:00:00Z")
    suspend fun getCountryDetail(
        @Path ("countryName") countryName: String,
        @Path ("firstDate") firstDate: String,
        @Path ("lastDate") lastDate: String
    ) : List<CountryDetailDTI>
}