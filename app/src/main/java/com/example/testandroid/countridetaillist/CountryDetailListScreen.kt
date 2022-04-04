package com.example.testandroid.countridetaillist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testandroid.data.models.CountryDetailDTOI

@Composable
fun CountryDetailListScreen(
    navController: NavController,
    countryName : String,
    viewModel: CountrydetailViewModel = hiltViewModel()
) {

    val countryDetail = produceState(initialValue = listOf<CountryDetailDTOI>() ) {
        value = viewModel.getCountryDetail(countryName)
    }
    Column() {
        Text(
            text = "Country: ${countryName}",
            fontSize = 32.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(countryDetail.value.size) {
                CountryDetailItem(countryDetail = countryDetail.value[it], navController = navController)
            }
        }
    }

}

@Composable
fun CountryDetailItem(
    countryDetail: CountryDetailDTOI,
    navController: NavController
) {
    Column( modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .padding(vertical = 16.dp)) {
        Text(
            text = "Date: ${countryDetail.date}",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            text = "Active: ${countryDetail.active}",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            text = "Confirmed: ${countryDetail.confirmed}",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            text = "Recovered: ${countryDetail.recovered}",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            text = "Deaths: ${countryDetail.deaths}",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }

}
