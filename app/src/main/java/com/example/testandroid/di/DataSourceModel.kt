package com.example.testandroid.di

import android.content.Context
import androidx.room.Room
import com.example.testandroid.BuildConfig
import com.example.testandroid.Constants.BASE_URL
import com.example.testandroid.datasource.CountryDao
import com.example.testandroid.datasource.DbDataSource
import com.example.testandroid.datasource.WebDataSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModel {


    @Singleton
    @Provides
    fun provideWebDataSource() : WebDataSource {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                })
            .build()
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
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