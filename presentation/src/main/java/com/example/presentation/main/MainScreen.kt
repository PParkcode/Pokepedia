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


/**
 * Navigation을 사용하기 위한 컴포저블 함수
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        // Detail로 이동할 때 포켓몬 ID와 한글 이름을 넘긴다.
        composable(Routes.Home.route) {
            SetHomeActivity(navigateToDetail = { id, name ->
                navController.navigate(Routes.Detail.route +"/$id/$name")
            })
        }

        composable(
            // 받아온 ID와 이름을 저장
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

