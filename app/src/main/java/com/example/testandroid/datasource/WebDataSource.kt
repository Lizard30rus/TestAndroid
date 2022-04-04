package com.example.testandroid.datasource


import com.example.testandroid.data.models.CountryDTOI
import com.example.testandroid.data.models.CountryDetailDTOI
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebDataSource{

    @GET ("countries")
    suspend fun updateCountries() : List<CountryDTOI>

    @GET ("country/{countrySlug}")
    suspend fun getCountryDetail(
        @Path ("countrySlug") countrySlug: String,
        @Query("from") firstDate : String,
        @Query("to") lastDate : String
    ) : List<CountryDetailDTOI>
}

