package com.example.testandroid.countridetaillist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testandroid.Constants
import com.example.testandroid.Response
import com.example.testandroid.data.models.CountryDetail
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

    @SuppressLint("NewApi", "LogNotTimber")
    suspend fun getCountryDetail(countrySlug : String) : List<CountryDetail> {

        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)
        val today = LocalDateTime.now().format(formatter)
        val twoWeeksAgo = LocalDateTime.now().minusWeeks(2L).format(formatter)

        val result = countryRepository.getCountryDetail(countrySlug, twoWeeksAgo, today)
        return when (result) {
            is Response.Success -> {
                result.data
            }
            is Response.Error -> {
                Log.e("Exception", "${result.error.message}", result.error)
                emptyList()
            }
        }
    }
}