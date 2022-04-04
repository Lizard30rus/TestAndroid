package com.example.testandroid.countrylist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testandroid.Response
import com.example.testandroid.data.models.Country
import com.example.testandroid.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@SuppressLint("LogNotTimber")
@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryRepository: CountryRepository
): ViewModel() {


    private val _countryList = MutableStateFlow<List<Country>>(listOf())
    val countryList : StateFlow<List<Country>> = _countryList

    private var cachedCountryList = listOf<Country>()
    private var isSearchStarting = true
    var isSearching = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            updateCountries()
            val result = countryRepository.getCountries()
            when (result) {
                is Response.Success -> {
                    result.data.collect{
                        _countryList.value = it
                    }
                }
                is Response.Error -> {
                    Log.e("Exception", "${result.error.message}", result.error)
                }
            }
        }
    }

    fun searchCountryList(query : String) {

        val listSearch = if (isSearchStarting) {
            countryList.value
        } else {
            cachedCountryList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                _countryList.value = cachedCountryList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
           val results = listSearch.filter {
               it.countryName.contains(query.trim(), ignoreCase = true)
           }
            if (isSearchStarting) {
                cachedCountryList = countryList.value
                isSearchStarting = false
            }
            _countryList.value = results
            isSearching.value = true
        }
    }

   private suspend fun updateCountries() {
       try {
           countryRepository.updateCountries()
       } catch (e: Exception) {
           println("ошибка при попытке обновить страны:  ${e.message}")
       }
   }
}