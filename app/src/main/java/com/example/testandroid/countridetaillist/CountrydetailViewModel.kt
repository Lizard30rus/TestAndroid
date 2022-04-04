package com.example.testandroid.countridetaillist

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.testandroid.Constants
import com.example.testandroid.data.models.CountryDetailDTOI
import com.example.testandroid.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CountrydetailViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    @SuppressLint("NewApi")
    suspend fun getCountryDetail(countrySlug : String) : List<CountryDetailDTOI>{

        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)
        val today = LocalDateTime.now().format(formatter)
        val twoWeeksAgo = LocalDateTime.now().minusWeeks(2L).format(formatter)
        try {

        } catch (e : Exception) {

        }
        return countryRepository.getCountryDetail(countrySlug, twoWeeksAgo, today)
    }

}