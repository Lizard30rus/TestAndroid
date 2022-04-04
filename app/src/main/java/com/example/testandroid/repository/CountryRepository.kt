package com.example.testandroid.repository

import com.example.testandroid.data.models.Country
import com.example.testandroid.data.models.CountryDetailDTOI
import kotlinx.coroutines.flow.Flow

interface CountryRepository {


    /**
     * Получаем список стран с Back, мапим и записваем в БД
     */
    suspend fun updateCountries()


    /**
     * Подписываемся на список стран с БД
     */
    fun getCountries() : Flow<List<Country>>


    /**
     * Получение списка дат с детальным описанием заболеваемости
     */
    suspend fun getCountryDetail(
        countrySlug: String,
        firstDate : String,
        lastDate: String)
    : List<CountryDetailDTOI>
}