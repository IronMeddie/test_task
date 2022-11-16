package com.ironmeddie.test_task.presentation.ui.Explorer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentExplorerBinding
import com.ironmeddie.test_task.domain.models.CategoryItem
import com.ironmeddie.test_task.presentation.ui.activity.MainActivity
import com.ironmeddie.test_task.presentation.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExplorerFragment : Fragment() {

    private var _binding: FragmentExplorerBinding? = null
    private val binding get() = _binding!!
    private val viewmodel by viewModels<ExplorerViewModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplorerBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as MainActivity).itemExpBottomMenu()
        val navController = findNavController()

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
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
                    if (bottomSheetState.isCollapsed) (activity as MainActivity).showBottomMenu()
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
                                        (activity as MainActivity).hideBottomMenu()
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
                                val listHot = viewmodel.hotSales.collectAsState()
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
                                val bestSellers = viewmodel.bestSellers.collectAsState()
                                ExplorerBestSellers(bestSellers.value,navController)
                            }
                            item(key = "Spacer") {
                                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.bottom_menu_height)))
                            }
                        }
                    }


                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}














