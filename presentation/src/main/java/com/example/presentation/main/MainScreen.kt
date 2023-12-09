package com.example.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.detail.SetDetailActivity
import com.example.presentation.home.SetHomeActivity
import com.example.presentation.navigation.Routes



@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            SetHomeActivity(navigateToDetail = { id, name ->
                navController.navigate(Routes.Detail.route +"/$id/$name")
            })
        }

        composable(
            route = Routes.Detail.route + "/{id}/{name}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // 인수 추출
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val name = backStackEntry.arguments?.getString("name") ?: ""

            SetDetailActivity(id = id, name = name)
        }
    }

}

