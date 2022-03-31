package com.example.testandroid.countrylist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testandroid.data.models.Country
import com.example.testandroid.data.models.CountryDTI
import com.example.testandroid.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryRepositoryImpl: CountryRepository
): ViewModel() {

    var countryList = mutableStateOf<List<Country>>(listOf())

    private var cachedCountryList = listOf<Country>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        viewModelScope.launch {
            updateCountries()
            //delay(5000L)
            countryRepositoryImpl.getCountries().collect {
                countryList.value = it
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
                countryList.value = cachedCountryList
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
            countryList.value = results
            isSearching.value = true
        }

    }

   private suspend fun updateCountries() {
       countryRepositoryImpl.updateCountries()
   }

}