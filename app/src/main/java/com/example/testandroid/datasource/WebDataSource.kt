package com.example.testandroid.datasource


import com.example.testandroid.data.models.CountryDTI
import com.example.testandroid.data.models.CountryDetailDTI
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface WebDataSource{

    @GET ("countries")
    suspend fun updateCountries() : List<CountryDTI>

    @GET ("country/{countrySlug}")
    suspend fun getCountryDetail(
        @Path ("countrySlug") countrySlug: String,
        @Query("from") firstDate : String,
        @Query("to") lastDate : String
    ) : List<CountryDetailDTI>
}