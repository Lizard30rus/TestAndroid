package com.example.testandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testandroid.Constants.COUNTRY_DETAIL_SCREEN
import com.example.testandroid.Constants.COUNTRY_LIST_SCREEN
import com.example.testandroid.Constants.COUNTRY_NAME_KEY
import com.example.testandroid.countrylist.CountryListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = COUNTRY_LIST_SCREEN) {
                composable(COUNTRY_LIST_SCREEN) {
                    CountryListScreen(navController = navController)
                }

                composable("$COUNTRY_DETAIL_SCREEN/{countryName}",
                    arguments = listOf(navArgument(COUNTRY_NAME_KEY) {
                        type = NavType.StringType })
                ) {
                    val countryName = remember {
                        it.arguments?.getString(COUNTRY_NAME_KEY)
                    }

                }
            }
        }
    }
}
