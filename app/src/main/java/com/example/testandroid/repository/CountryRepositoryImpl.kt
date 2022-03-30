package com.example.testandroid.repository

import android.annotation.SuppressLint
import com.cesarferreira.tempo.Tempo
import com.example.testandroid.data.models.Country
import com.example.testandroid.data.models.CountryDetailDTI
import com.example.testandroid.datasource.CountryDao
import com.example.testandroid.datasource.WebDataSource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val webDataSource: WebDataSource,
    private val countryDao: CountryDao
)
: CountryRepository {


    override suspend fun updateCountries() {

        val resultDTI = webDataSource.updateCountries()

        val result = mutableListOf<Country>()
        for (i in resultDTI.indices) {
            result.add(Country(resultDTI[i].countryName,resultDTI[i].slug))
        }
        countryDao.insertCountries(result)
    }

    override fun getCountries(): Flow<List<Country>> {
        return countryDao.getCountries()
    }

    @SuppressLint("NewApi")
    override suspend fun getCountryDetail(
        countryName: String,
        firstDate: String,
        lastDate: String
    ): List<CountryDetailDTI> {

        val result = webDataSource.getCountryDetail(countryName, firstDate, lastDate)
        return result
    }
}