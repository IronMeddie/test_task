package com.ironmeddie.test_task.presentation.ui.Explorer


import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.*
import androidx.navigation.NavController
import com.ironmeddie.data.DataResource
import com.ironmeddie.domain.models.CategoryItem
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.activity.Categories
import com.ironmeddie.test_task.presentation.ui.base.ReconnectButton
import com.ironmeddie.test_task.presentation.ui.cart.LoadingText
import com.ironmeddie.test_task.presentation.ui.theme.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExplorerScreen(
    showBottomMenu: () -> Unit,
    hideBottomMenu: () -> Unit,
    viewModel: ExplorerViewModel = hiltViewModel(),
    bottoomPadding: Dp,
    navController: NavController
) {
    MyTheme {
        val categories =
                listOf(
                    CategoryItem(Categories.CATEGORY_PHONES, R.drawable.phone),
                    CategoryItem(Categories.CATEGORY_COMPUTER, R.drawable.computer),
                    CategoryItem(Categories.CATEGORY_HEALTH, R.drawable.health),
                    CategoryItem(Categories.CATEGORY_BOOKS, R.drawable.books),
                    CategoryItem(Categories.CATEGORY_SSD, R.drawable.phone),
                )



        val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
        val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
        val scope = rememberCoroutineScope()
        val data = viewModel.mainScreeData.collectAsState().value
        var category by rememberSaveable{ mutableStateOf(Categories.CATEGORY_PHONES) }

        if (bottomSheetState.isCollapsed && !bottomSheetState.isAnimationRunning) showBottomMenu()
        else hideBottomMenu()

        BottomSheetScaffold(
            sheetContent = {
                ExplorerFilter(bottomSheetState)
            },
            sheetShape = RoundedCornerShape(30.dp),
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetElevation = 20.dp,

        )
        { paddings ->


            LazyColumn(modifier = Modifier.padding(paddings)) {
                item(key = "ExplorerTopBar"){
                    ExplorerTopBar() {
                        scope.launch {
                            bottomSheetState.expand()
                        }
                    }
                }
                item(key = "Headers Select Category") {
                    ExplorerHeaders(
                        name = "Select Category",
                        "view all",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp)
                            .fillMaxWidth()
                    )
                }
                item(key = "VerticalCategoryList") {
                    ExplorerCategories(categories, category) {
                        category = it
                    }
                }
                item(key = "SearhPanel") {
                    ExplorerSearch()
                }
                item(key = "Headers Hot Sales") {
                    ExplorerHeaders(
                        "Hot Sales",
                        "see more",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp, top = 24.dp)
                            .fillMaxWidth()
                    )
                }
                item(key = "Carusel") {


                    when(data){
                        is DataResource.Loading -> LoadingText()
                        is DataResource.Failure -> {
                            ReconnectButton { viewModel.getInfo() }
                        }
                        is DataResource.Success ->{
//                            val state = remember {
//                                MutableTransitionState(false).apply { targetState = true }
//                            }
//                            AnimatedVisibility(visibleState = state, enter = slideInHorizontally(), exit = slideOutHorizontally()) {
                                ExplorerCarusel(data.value.home_store, navController)
//                            }
                        }

                    }
                }
                item(key = "Headers Best Seller") {
                    ExplorerHeaders(
                        "Best Seller", "see more",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )
                }

                if (data is DataResource.Success) {
                    val list = data.value.best_seller
                    for (i in list.indices step 2){
                        item(key = list[i].id) {
                            Row(modifier = Modifier.padding(top = 15.dp)) {
                                BestSellerItem(list[i],navController, true)
                                if (i+1<list.size) BestSellerItem(list[i+1],navController)
                            }
                        }
                    }

                }
                item(key = "Spacer") {
                    Spacer(modifier = Modifier.size(bottoomPadding))
                }

            }


        }
    }


}















