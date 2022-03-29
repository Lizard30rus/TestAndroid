package com.example.testandroid.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * модель детального описания страны для записи данных с БД
 */
@Entity
data class CountryDetail(
    @PrimaryKey val countryName : String,
    val counfirmed : String,
    val deaths : String,
    val recovered : String,
    val active : String,
    //val date : Date
)