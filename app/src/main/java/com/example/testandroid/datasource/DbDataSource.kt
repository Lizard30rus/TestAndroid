package com.example.testandroid.datasource

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testandroid.data.models.Country
import com.example.testandroid.data.models.CountryDetail

@Database(entities = [Country::class], version = 2)
abstract class DbDataSource : RoomDatabase() {


    abstract fun CountryDao() : CountryDao
}