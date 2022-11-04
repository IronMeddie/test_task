package com.ironmeddie.test_task

import android.location.GnssAntennaInfo.Listener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ironmeddie.test_task.databinding.ActivityMainBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var cartItems = 0  //  счетчик добавленных  в корзину. потом удалить

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.darkblue_app)
        window.navigationBarColor = getColor(R.color.darkblue_app)
        setContentView(R.layout.splash_screen)


        MainScope().launch {
            delay(2000)
            window.statusBarColor = getColor(R.color.background_main)
            window.navigationBarColor = getColor(R.color.background_main)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.bottomNavMenu.setItemSelected(R.id.navigation_explorer)
            val bottomMenu = binding.bottomNavMenu
            val navController = findNavController(R.id.nav_host)
            bottomMenu.setOnItemSelectedListener {
                navController.navigate(it)
                navController.addOnDestinationChangedListener { controller, destination, arguments -> controller.popBackStack() }

            }





//            cartItems = 3
//            val badge = binding.bottomNavMenu.getOrCreateBadge(R.id.navigation_cart)
//            if (cartItems > 0){
//                badge.isVisible = true
//                badge.number = cartItems
//            }else{
//                binding.bottomNavMenu.removeBadge(R.id.navigation_cart)
//            }
        }
    }

    fun adedtoCart(){
        cartItems++
    }

}