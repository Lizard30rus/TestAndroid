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
import com.example.testandroid.countrylist.CountryListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "country_list_screen") {
                composable("country_list_screen") {
                    CountryListScreen(navController = navController)
                }

                composable("country_detail_screen/{countryName}",
                    arguments = listOf(navArgument("countryName") {
                        type = NavType.StringType })
                ) {
                    val countryName = remember {
                        it.arguments?.getString("countryName")
                    }

                }
            }
        }
    }
}
