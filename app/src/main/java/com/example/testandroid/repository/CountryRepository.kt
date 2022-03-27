package com.example.testandroid.repository

import android.database.Observable
import com.example.testandroid.data.remote.CountryApi
import com.example.testandroid.data.remote.models.Country
import com.example.testandroid.data.remote.models.CountryDetail
import com.example.testandroid.database.CountryDao
import com.example.testandroid.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import java.util.*
import javax.inject.Inject

@ActivityScoped
class CountryRepository @Inject constructor(
    private val api : CountryApi,
    private val countryDao : CountryDao
){

    //Coroutines
    suspend fun updateCountryList() : Resource <List<Country>>  {
        val result = try {
            api.getCountries()
        } catch (e: Exception) {
            return Resource.Error("updateCountryList: error")
        }
        countryDao.setCountries(result)
        return Resource.Success(result)
    }

    suspend fun getCountryFromDatabase() : Resource <List<Country>> {
        val result = try {
            countryDao.getCountries()
        } catch (e: Exception) {
            return Resource.Error("Get countries from database: error")
        }
        return Resource.Success(result)
    }

    suspend fun getCountryInfo(countryName : String) : Resource <CountryDetail> {
        val result = try {
            api.getCountryDetail(countryName)
        } catch (e: Exception) {
            return Resource.Error("getCountryInfo: error")
        }
        return Resource.Success(result)
    }

    //RxJava
   /* fun updateCountryList() : Resource <Observable<List<Country>>> {
        val result = try {
            api.getCountries()
        } catch (e: Exception) {
            return Resource.Error("updateCountryList: error")
        }
        return Resource.Success(result)
    }

    fun getCountryInfo(countryName : String) : Resource <Observable<CountryDetail>> {
        val result = try {
            api.getCountryDetail(countryName)
        } catch (e: Exception) {
            return Resource.Error("getCountryInfo: error")
        }
        return Resource.Success(result)
    }*/
}