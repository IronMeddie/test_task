package com.ironmeddie.test_task.presentation.ui.cart


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme


@Composable
fun CartScreen(cartViewModel: CartViewModel = hiltViewModel(), navController: NavController) {
    MyTheme {
        val cart = cartViewModel.cart.collectAsState()

        Scaffold(topBar = {
            CartTopBar(
                R.drawable.ic_arrow_back,
                R.drawable.location,
                navController
            )
        }) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        text = "My Cart",
                        style = MaterialTheme.typography.h2,
                        fontSize = 35.sp,
                        modifier = Modifier.padding(horizontal = 42.dp, vertical = 50.dp)
                    )
                }

                item {
                    CartList(cart.value)
                }
            }

        }
    }
}


