package com.example.testandroid.repository

import com.example.testandroid.data.models.Country
import com.example.testandroid.datasource.CountryDao
import com.example.testandroid.datasource.WebDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val webDataSource: WebDataSource,
    private val countryDao: CountryDao
)
: CountryRepository {


    override suspend fun updateCountries() : List<Country> {

        val resultDTI = webDataSource.updateCountries()

        val result = mutableListOf<Country>()
        for (i in resultDTI.indices) {
            result.add(Country(resultDTI[i].countryName,resultDTI[i].slug))
        }
        return result
        //countryDao.insertCountries(result)
    }

    override fun getCountries(): Flow<List<Country>> {
        return countryDao.getCountries()
    }
}