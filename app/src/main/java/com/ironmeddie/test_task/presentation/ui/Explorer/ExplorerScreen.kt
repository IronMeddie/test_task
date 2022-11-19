package com.ironmeddie.test_task.presentation.ui.Explorer


import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.*
import androidx.navigation.NavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.domain.models.CategoryItem
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
){
    MyTheme {
        val categories by remember {
            mutableStateOf(
                listOf(
                    CategoryItem("Phones", R.drawable.phone),
                    CategoryItem("Computer", R.drawable.computer),
                    CategoryItem("Health", R.drawable.health),
                    CategoryItem("Books", R.drawable.books),
                    CategoryItem("SSD", R.drawable.phone),
                )
            )
        }

        val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
        val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
        val scope = rememberCoroutineScope()

        if (bottomSheetState.isCollapsed && !bottomSheetState.isAnimationRunning) showBottomMenu()
        else hideBottomMenu()

        BottomSheetScaffold(
            sheetContent = {
                ExplorerFilter(bottomSheetState)
            },
            sheetShape = RoundedCornerShape(30.dp),
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetElevation = 20.dp
        )
        { paddings ->
            LazyColumn(modifier = Modifier.padding(paddings)) {
                item(key = "topbar") {
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
                    ExplorerCategories(categories) {

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
                    val listHot = viewModel.hotSales.collectAsState()
                    AnimatedVisibility(
                        visible = listHot.value.isNotEmpty(),
                        enter = slideInHorizontally(),
                        exit = slideOutHorizontally()
                    ) {
                        ExplorerCarusel(listHot.value, navController)
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
                item(key = "bestSellers") {
                    val bestSellers = viewModel.bestSellers.collectAsState()
                    ExplorerBestSellers(bestSellers.value,navController)
                }

                item(key = "Spacer") {
                    Spacer(modifier = Modifier.size(bottoomPadding))
                }
            }
        }


    }
}














