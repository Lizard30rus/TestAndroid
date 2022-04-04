package com.example.testandroid.repository

import android.annotation.SuppressLint
import com.example.testandroid.Response
import com.example.testandroid.data.models.Country
import com.example.testandroid.data.models.CountryDetail
import com.example.testandroid.data.models.CountryDetailDTOI
import com.example.testandroid.datasource.CountryDao
import com.example.testandroid.datasource.WebDataSource
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val webDataSource: WebDataSource,
    private val countryDao: CountryDao
)
: CountryRepository {


    override suspend fun updateCountries() {
        try {
            val resultDTOI = webDataSource.updateCountries()
            val result = mutableListOf<Country>()
            resultDTOI.forEach { item ->
                result.add(Country(item.countryName,item.slug))
            }
            countryDao.insertCountries(result)
        } catch (e : Exception) {
            println(e.message)
        }
    }

    override fun getCountries(): Response<Flow<List<Country>>> {
        val result = countryDao.getCountries()
        return try {
            Response.Success(result)
        } catch (e : Exception) {
            Response.Error(e)
        }
    }

    @SuppressLint("NewApi")
    override suspend fun getCountryDetail(
        countrySlug : String,
        firstDate : String,
        lastDate : String
    ): Response<List<CountryDetail>> {
        val resultDTOI = webDataSource.getCountryDetail(countrySlug, firstDate, lastDate)
        val result = mutableListOf<CountryDetail>()
        resultDTOI.forEach {item ->
            result.add(CountryDetail(item.countryName, item.confirmed, item.deaths,item.recovered, item.active, item.date))
        }
        return try {
            Response.Success(result)
        } catch (e : Exception) {
            Response.Error(e)
        }
    }
}