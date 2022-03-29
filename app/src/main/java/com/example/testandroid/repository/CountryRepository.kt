package com.example.testandroid.repository

import com.example.testandroid.data.models.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {


    /**
     * Получаем список стран с Back, мапим и записваем в БД
     */
    suspend fun updateCountries() : List<Country>


    /**
     * Подписываемся на список стран с БД
     */
    fun getCountries() : Flow<List<Country>>
}