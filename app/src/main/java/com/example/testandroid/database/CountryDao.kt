package com.example.testandroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testandroid.data.remote.models.Country


@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCountries (countries : List<Country>)

    @Query("SELECT * FROM country")
    fun getCountries() : LiveData<List<Country>>
}