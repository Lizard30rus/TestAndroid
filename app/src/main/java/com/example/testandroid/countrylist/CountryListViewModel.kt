package com.example.testandroid.countrylist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testandroid.data.remote.models.Country
import com.example.testandroid.repository.CountryRepository
import com.example.testandroid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository : CountryRepository
): ViewModel() {

    var countryList = mutableStateOf<List<Country>>(listOf())
    var loadError = mutableStateOf("")

    init {
       loadCountryList()
    }

    //coroutines
   fun loadCountryList() {
        viewModelScope.launch {
            val result = repository.updateCountryList()
            Thread.sleep(3000)

            when (result) {
                is Resource.Success -> {
                    countryList.value = result.data!!
                }
                is Resource.Error -> {
                    loadError.value = result.message!!

                }
            }
        }
    }

    //RxJava
    /*fun loadCountryList() {

        val result = repository.updateCountryList()
        when(result) {
            is Resource.Success -> {
                val dispose = result.data.
            }
            is Resource.Error -> {

            }
        }

    }*/

}