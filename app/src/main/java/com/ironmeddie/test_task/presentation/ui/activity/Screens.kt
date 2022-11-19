package com.ironmeddie.test_task.presentation.ui.activity


sealed class Screens(val route: String){
    object Explorer : Screens("Explorer")
    object Cart : Screens("Cart")
    object Favorite : Screens("Favorite")
    object Profile : Screens("Profile")
    object Details : Screens("Details")
    object Splash : Screens("Splash")
}
