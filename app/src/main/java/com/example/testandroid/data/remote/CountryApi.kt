package com.example.testandroid.data.remote

import android.database.Observable
import com.example.testandroid.data.remote.models.Country
import com.example.testandroid.data.remote.models.CountryDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CountryApi {


    //Coroutines
    @GET("countries")
    suspend fun getCountries() : List<Country>

    @GET("total/country/{country}")
    suspend fun getCountryDetail(
        @Path("country") countryName : String
    ) : CountryDetail

    //RxJava
    /*@GET
    fun getCountries(): Observable<List<Country>>

    @GET
    fun getCountryDetail(
        @Path("country") countryName : String
    ) : Observable<CountryDetail>*/
}