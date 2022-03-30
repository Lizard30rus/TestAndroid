package com.example.testandroid.countrylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testandroid.data.models.Country

@Composable
fun CountryListScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Spacer(modifier = Modifier.height(20.dp))

        SearchBar(
            hint = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        CountryList(navController = navController)
        }
    }
}

@Composable
fun CountryList(
    navController: NavController,
    viewModel: CountryListViewModel = hiltViewModel()
) {
    val countryList = viewModel.countryList.value
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(countryList.size) {
            CountryItem(country = countryList[it], navController = navController)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.LightGray, CircleShape)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
        if (isHintDisplayed) {
            Text(text = hint,
            color = Color.DarkGray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun CountryItem(
    country: Country,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(color = Color.LightGray)
        .padding(vertical = 16.dp)
        .clickable {
            navController.navigate("country_detail_screen/${country.countryName}")
        }
    ){
        Text(
            text = "Country: ${country.countryName}",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
            )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        Text(text = "Slug: ${country.slug}",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
    }



}