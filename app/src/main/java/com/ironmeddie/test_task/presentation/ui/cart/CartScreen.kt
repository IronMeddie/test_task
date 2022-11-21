package com.ironmeddie.test_task.presentation.ui.cart


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ironmeddie.data.DataResource
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.base.ReconnectButton
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CartScreen(cartViewModel: CartViewModel = hiltViewModel(), navController: NavController) {
    MyTheme {
        val cart = cartViewModel.cart.collectAsState().value


        Scaffold(topBar = {
            CartTopBar(
                R.drawable.ic_arrow_back,
                R.drawable.location,
                navController
            )
        }) {


            when (cart) {
                is DataResource.Loading -> LoadingText()
                is DataResource.Failure -> ReconnectButton { cartViewModel.getInfo() }
                is DataResource.Success -> {

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
                                modifier = Modifier.padding(
                                    horizontal = 42.dp,
                                    vertical = 50.dp
                                )
                            )
                        }
                        item {
                            val state = remember {
                                MutableTransitionState(false).apply { targetState = true }
                            }
                            AnimatedVisibility(visibleState = state, enter = scaleIn()) {
                                if (cart.value.basket.isEmpty()) {
                                    Box(
                                        Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                    ) {
                                        Text(text = "Basket is empty")
                                    }
                                } else {
                                    CartList(cart.value)
                                }

                            }
                        }

                    }
                }
            }
        }

    }

}

@Composable
fun LoadingText() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(200.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading..")
    }
}


