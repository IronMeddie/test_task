package com.ironmeddie.test_task.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.Explorer.ExplorerBottomMenu
import com.ironmeddie.test_task.presentation.ui.Explorer.ExplorerScreen
import com.ironmeddie.test_task.presentation.ui.cart.CartScreen
import com.ironmeddie.test_task.presentation.ui.details.DetailScreen
import com.ironmeddie.test_task.presentation.ui.favorite.FavoriteScreen
import com.ironmeddie.test_task.presentation.ui.profile.ProfileScreen
import com.ironmeddie.test_task.presentation.ui.splash.SplashScreen
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val vm by viewModels<ActivityViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyTheme {
                val splash = vm.splashState.collectAsState().value
                if (!splash) {
                    SplashScreen()
                    window.statusBarColor = getColor(R.color.darkblue_app)
                    window.navigationBarColor = getColor(R.color.darkblue_app)
                    vm.splash()
                } else {
                    window.statusBarColor = getColor(R.color.background_main)
                    window.navigationBarColor = getColor(R.color.background_main)
                    val navController = rememberNavController()
                    var bottomMenuState by remember { mutableStateOf(true) }
                    Scaffold(bottomBar = {
                        AnimatedVisibility(
                            visible = bottomMenuState,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            ExplorerBottomMenu(navController = navController) {
                                navController.navigate(it) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }) { paddings ->

                        NavHost(
                            navController = navController,
                            startDestination = Screens.Explorer.route
                        ) {
                            composable(Screens.Explorer.route) {
                                ExplorerScreen(
                                    hideBottomMenu = { bottomMenuState = false },
                                    showBottomMenu = { bottomMenuState = true },
                                    navController = navController,
                                    bottoomPadding = paddings.calculateBottomPadding()
                                )
                            }
                            composable(Screens.Cart.route) {
                                bottomMenuState = false
                                CartScreen(navController = navController) }
                            composable(Screens.Favorite.route) { FavoriteScreen() }
                            composable(Screens.Profile.route) { ProfileScreen() }
                            composable(Screens.Details.route) {
                                bottomMenuState = false
                                DetailScreen(navController = navController) }
                        }
                    }
                }


            }
        }


    }





}


