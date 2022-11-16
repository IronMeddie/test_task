package com.ironmeddie.test_task.presentation.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val vm by viewModels<ActivityViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.splash_screen)
        window.statusBarColor = getColor(R.color.darkblue_app)
        window.navigationBarColor = getColor(R.color.darkblue_app)



        lifecycleScope.launchWhenStarted {
            vm.splashState.collect {
                if (!it) {
                    delay(2000)
                    vm.splash()
                }else{
                    window.statusBarColor = getColor(R.color.background_main)
                    window.navigationBarColor = getColor(R.color.background_main)
                    setContentView(binding.root)

                    val bottomMenu = binding.bottomNavMenu
                    val navController = findNavController(R.id.nav_host)
                    bottomMenu.setOnItemSelectedListener {
                        if (navController.currentDestination?.id != it) {
                            val builder = NavOptions.Builder()
                                .setPopUpTo(R.id.mobile_navigation, inclusive = true, true)
                            val options = builder.build()
                            if (it == R.id.navigation_cart) navController.navigate(it)
                            else navController.navigate(it, null, options)
                        }
                    }

                }
            }
        }



        lifecycleScope.launchWhenStarted {
            vm.cart.collect {
                cartBadge(it)
            }
        }


    }


    fun hideBottomMenu() {
        lifecycleScope.launchWhenCreated {
            binding.bottomNavMenu.visibility = View.GONE
        }
    }

    fun cartBadge(int: Int) {
        if (int > 0) {
            binding.bottomNavMenu.showBadge(R.id.navigation_cart, int)
        } else binding.bottomNavMenu.dismissBadge(R.id.navigation_cart)
    }

    fun showBottomMenu() {
        lifecycleScope.launchWhenCreated {
            binding.bottomNavMenu.visibility = View.VISIBLE
        }
    }

    fun itemExpBottomMenu() {
        lifecycleScope.launchWhenCreated {
            binding.bottomNavMenu.setItemSelected(R.id.navigation_explorer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}


