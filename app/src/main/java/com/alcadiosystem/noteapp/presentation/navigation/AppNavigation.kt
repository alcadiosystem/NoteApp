package com.alcadiosystem.noteapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alcadiosystem.noteapp.presentation.view.addnote.AddScreen
import com.alcadiosystem.noteapp.presentation.view.home.HomeScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route){
        composable(route = Screens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = Screens.AddScreen.route){
            AddScreen(navController)
        }

        composable(route = Screens.UpdateScreen.route, arguments = listOf(
            navArgument(name="id"){
                type = NavType.IntType
            }
        )){
            AddScreen(navController = navController)
        }
    }
}

sealed class Screens(val route:String){
    data object HomeScreen:Screens("home")
    data object AddScreen:Screens("add")
    data object UpdateScreen:Screens("update/{id}"){
        fun getRouteId(id:Int) = "update/$id"
    }
}