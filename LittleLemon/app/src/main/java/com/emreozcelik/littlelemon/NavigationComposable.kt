package com.emreozcelik.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(navController: NavHostController, database: AppDatabase) {


    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ){
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }
        composable(Home.route){
           Home(navController,database)
        }
    }

}