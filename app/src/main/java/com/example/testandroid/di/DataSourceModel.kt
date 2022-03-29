package com.example.testandroid.di

import android.content.Context
import androidx.room.Room
import com.example.testandroid.Constants.BASE_URL
import com.example.testandroid.datasource.CountryDao
import com.example.testandroid.datasource.DbDataSource
import com.example.testandroid.datasource.WebDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModel {


    @Singleton
    @Provides
    fun provideWebDataSource() : WebDataSource {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WebDataSource::class.java)
    }

    @Singleton
    @Provides
    fun DbDataSource(@ApplicationContext context: Context) : DbDataSource {
         return Room
             .databaseBuilder(context,
             DbDataSource::class.java,
             "country_database")
             .fallbackToDestructiveMigration()
             .build()
    }

    @Singleton
    @Provides
    fun countryDao(db : DbDataSource): CountryDao = db.CountryDao()
}