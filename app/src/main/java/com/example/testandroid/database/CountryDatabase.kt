package com.example.testandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testandroid.data.remote.models.Country


@Database (entities = [Country::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao() : CountryDao
}