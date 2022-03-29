package com.example.testandroid.di

import com.example.testandroid.repository.CountryRepository
import com.example.testandroid.repository.CountryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun countryRepository(impl : CountryRepositoryImpl) : CountryRepository
}