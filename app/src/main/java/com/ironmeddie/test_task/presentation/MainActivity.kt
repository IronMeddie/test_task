package com.ironmeddie.test_task.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
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

            bottomMenu.setItemSelected(R.id.navigation_explorer)
            bottomMenu.setOnItemSelectedListener {
                    val builder = NavOptions.Builder()
                        .setPopUpTo(R.id.mobile_navigation,inclusive = true,true)
                    val options = builder.build()
                    navController.navigate(it, null, options)
            }
        }
    }


    fun hideBottomMenu(){
        binding.bottomNavMenu.visibility = View.GONE
    }
    fun showBottomMenu(){
        binding.bottomNavMenu.visibility = View.VISIBLE
    }


    fun adedtoCart() {
        cartItems++
    }


}


