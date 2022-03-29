package com.example.testandroid.countrylist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testandroid.data.models.Country
import com.example.testandroid.data.models.CountryDTI
import com.example.testandroid.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryRepositoryImpl: CountryRepository
): ViewModel() {

    var countryList = mutableStateOf<List<Country>>(listOf())

    init {
        updateCountries()
    }

   private fun updateCountries() {
        viewModelScope.launch {
           countryList.value = countryRepositoryImpl.updateCountries()
            /*countryRepositoryImpl.getCountries().collect {
                countryList.value = it
            }*/
        }
   }

}