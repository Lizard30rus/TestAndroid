package com.example.testandroid.di

import android.content.Context
import androidx.room.Room
import com.example.testandroid.Constants.BASE_URL
import com.example.testandroid.data.remote.CountryApi
import com.example.testandroid.database.CountryDao
import com.example.testandroid.database.CountryDatabase
import com.example.testandroid.repository.CountryRepository
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
object AppModule {

    @Singleton
    @Provides
    fun provideCountryRepository(
        api : CountryApi
    ) = CountryRepository(api)


    //Coroutines
    @Singleton
    @Provides
    fun provideCountryApi() : CountryApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CountryApi::class.java)
    }

    //RxJava
    /*@Singleton
    @Provides
    fun provideCountryApi() : CountryApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CountryApi::class.java)
    }*/

    //Room
    @Singleton
    @Provides
    fun CountryDatabase(@ApplicationContext context: Context) : CountryDatabase {
         return Room
             .databaseBuilder(context,
             CountryDatabase::class.java,
             "country_database")
             .fallbackToDestructiveMigration()
             .build()
    }

    @Singleton
    @Provides
    fun countryDao(db : CountryDatabase): CountryDao = db.countryDao()
}