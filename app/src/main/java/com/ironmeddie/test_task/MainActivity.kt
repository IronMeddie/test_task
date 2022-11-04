package com.ironmeddie.test_task

import android.location.GnssAntennaInfo.Listener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.get
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

    var cartItems = 5  //  счетчик добавленных  в корзину. потом удалить

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
            val bottomMenu = binding.bottomNavMenu
            val navController = findNavController(R.id.nav_host)


            bottomMenu.setOnItemSelectedListener {
                navController.navigate(it)
                navController.addOnDestinationChangedListener { controller, destination, arguments -> controller.popBackStack(it,true,true) }
            }
            bottomMenu.setItemSelected(R.id.navigation_explorer)
            bottomMenu.showBadge(R.id.navigation_cart,cartItems)
        }
    }

    fun adedtoCart(){
        cartItems++
    }

}