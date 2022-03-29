package com.example.testandroid.datasource


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testandroid.data.models.Country
import kotlinx.coroutines.flow.Flow


@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries : List<Country>)


    @Query("SELECT * FROM country")
    fun getCountries() : Flow<List<Country>>
}