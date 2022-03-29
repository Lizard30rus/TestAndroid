package com.example.testandroid.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * модель страны для записи данных с БД
 */
@Entity
data class Country(
    @PrimaryKey val countryName : String,
    val slug : String
)
