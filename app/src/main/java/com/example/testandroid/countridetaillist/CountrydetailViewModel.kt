package com.example.testandroid.countridetaillist

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testandroid.data.models.Country
import com.example.testandroid.data.models.CountryDetailDTI
import com.example.testandroid.repository.CountryRepository
import com.example.testandroid.repository.CountryRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CountrydetailViewModel @Inject constructor(
    private val countryRepositoryImpl: CountryRepository
) : ViewModel() {

    @SuppressLint("NewApi")
    suspend fun getCountryDetail(countryName : String) : List<CountryDetailDTI>{

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val today = LocalDateTime.now().format(formatter)
        val twoWeeksAgo = LocalDateTime.now().minusWeeks(2L).format(formatter)
        return countryRepositoryImpl.getCountryDetail(countryName, today, twoWeeksAgo )
    }

}