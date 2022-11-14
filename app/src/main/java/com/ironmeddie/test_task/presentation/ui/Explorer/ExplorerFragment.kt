package com.ironmeddie.test_task.presentation.ui.Explorer


import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentExplorerBinding
import com.ironmeddie.test_task.domain.models.BestSeller
import com.ironmeddie.test_task.domain.models.CategoryItem
import com.ironmeddie.test_task.domain.models.HomeStore
import com.ironmeddie.test_task.presentation.MainActivity
import com.ironmeddie.test_task.presentation.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExplorerFragment : Fragment() {

    private var _binding: FragmentExplorerBinding? = null
    private val binding get() = _binding!!
    private val viewmodel by viewModels<ExplorerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplorerBinding.inflate(inflater, container, false)
        val view = binding.root

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
                    Phototes(categories)
                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun Phototes(categories: List<CategoryItem>) {


        val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
        val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
        val scope = rememberCoroutineScope()
        if (bottomSheetState.isCollapsed)(activity as MainActivity).showBottomMenu()
        BottomSheetScaffold(
             sheetContent = {
                Filter()
            }, sheetShape = RoundedCornerShape(30.dp), scaffoldState = scaffoldState, sheetPeekHeight = 0.dp, sheetElevation = 20.dp
        )
        { paddings ->
            LazyColumn(modifier = Modifier.padding(paddings)) {
                item {
                    topBar(){
                        scope.launch {
                            (activity as MainActivity).hideBottomMenu()
                            bottomSheetState.expand()
                        }
                    }
                }
                item {
                    Headers(
                        name = "Select Category",
                        "view all",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp)
                            .fillMaxWidth()
                    )
                }
                item {
                    VerticalCategoryList(categories) {

                    }
                }
                item {
                    SearhPanel()
                }
                item {
                    Headers(
                        "Hot Sales",
                        "see more",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp, top = 24.dp)
                            .fillMaxWidth()
                    )
                }
                item {
                    val listHot = viewmodel.hotSales.collectAsState()
//                        listOf(
//                        HomeStore(1, true, true,"https://img.ibxk.com.br/2020/09/23/23104013057475.jpg?w=1120&h=420&mode=crop&scale=bot","Súper. Mega. Rápido.","Iphone 12"),
//                        HomeStore(2, true, false,"https://cdn-2.tstatic.net/kupang/foto/bank/images/pre-order-samsung-galaxy-a71-laris-manis-inilah-rekomendasi-ponsel-harga-rp-6-jutaan.jpg","Súper. Mega. Rápido.","Samsung Galaxy A71"))
                    Carusel(listHot.value)
                }
                item {
                    Headers(
                        "Best Seller", "see more",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    )
                }
                item {
                    val bestSellers = viewmodel.bestSellers.collectAsState()
//                    val list = listOf(BestSeller(1500, 111,true,"https://shop.gadgetufa.ru/images/upload/52534-smartfon-samsung-galaxy-s20-ultra-12-128-chernyj_1024.jpg",1047, "Samsung Galaxy s20 Ultra"),
//                        BestSeller(400, 222,true,"https://mi92.ru/wp-content/uploads/2020/03/smartfon-xiaomi-mi-10-pro-12-256gb-global-version-starry-blue-sinij-1.jpg",300, "Xiaomi Mi 10 Pro"))
                    bestSellers(bestSellers.value)
                }
                item {
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.bottom_menu_height)))
                }
            }
        }

    }


    @Composable
    private fun Headers(name: String, goTo: String, modifier: Modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h2
            )
            Text(
                text = goTo,
                style = MaterialTheme.typography.h3
            )
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    private fun VerticalCategoryList(
        categories: List<CategoryItem>,
        onItemChanged: (item: CategoryItem) -> Unit
    ) {
        var isselected by rememberSaveable { mutableStateOf(0) }
        val listState = rememberLazyListState()
        LazyRow(
            modifier = Modifier.padding(top = 24.dp),
            contentPadding = PaddingValues(horizontal = 12.dp), state = listState,
        ) {
            itemsIndexed(categories, key = { index, item -> item.name}) { i, item ->
                KategotyItem(item, i == isselected) { itemNEw ->
                        isselected = categories.indexOf(itemNEw)
                        onItemChanged(itemNEw)
                }
            }
        }

    }


    @Composable
    private fun KategotyItem(
        item: CategoryItem,
        isselected: Boolean,
        choose: (item: CategoryItem) -> Unit
    ) {
        val initialColor = MaterialTheme.colors.primary
        val targetColor = MaterialTheme.colors.primaryVariant

        val animatecolor = remember { Animatable(initialColor) }
        if (isselected) {
            LaunchedEffect(animatecolor) {
                animatecolor.animateTo(
                    targetValue = targetColor, animationSpec = tween(500)
                )
            }
        } else LaunchedEffect(animatecolor) {
            animatecolor.animateTo(
                targetValue = initialColor,
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(horizontal = 11.dp)
            .clickable {
                choose(item)
            }) {
            Box(
                modifier = Modifier
                    .shadow(20.dp, CircleShape, spotColor = Shadow)
                    .clip(CircleShape)
                    .size(71.dp)
                    .background(color = animatecolor.asState().value)
                    , contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = null,
                    tint = if (isselected) colorResource(R.color.white) else colorResource(id = R.color.GreyIcons),
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(
                text = item.name,
                color = if (isselected) colorResource(id = R.color.orange_app) else colorResource(
                    id = R.color.darkblue_app
                ),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(top = 7.dp)
            )
        }
    }


    @Composable
    private fun topBar(filter : () -> Unit) {
        Row(
            modifier = Modifier
                .height(74.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.padding(start = 35.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primaryVariant, modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "Zihuatanejo, Gro",
                    color = colorResource(id = R.color.darkblue_app),
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(start = 11.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .width(10.dp)
                        .height(5.dp),
                    tint = colorResource(id = R.color.Grey1)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 35.dp)
                    .size(15.dp)
                    .clickable { filter() },
                tint = colorResource(id = R.color.darkblue_app)
            )

        }
    }


    @Composable
    private fun SearhPanel() {
        var search by remember {
            mutableStateOf("")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp, start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth(0.8f),
                shape = MaterialTheme.shapes.large,
                colors = MyAppTextFieldColors(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "search_icon_input",
                        tint = colorResource(id = R.color.orange_app),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                    )
                },
                label = {
                    Text(
                        text = "Search",
                        color = colorResource(id = R.color.textColorSearch),
                        style = MaterialTheme.typography.h4,
                    )

                },
                value = search,
                onValueChange = { search = it },
            )
            Box(modifier = Modifier.padding()) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                        .background(MaterialTheme.colors.primaryVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.group_1),
                        contentDescription = null,
                        modifier = Modifier.size(23.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }

        }

    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    private fun Carusel(homeStore: List<HomeStore>) {
        HorizontalPager(
            count = homeStore.size, modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .fillMaxWidth()
        ) { page ->
            Card(shape = RoundedCornerShape(10.dp)) {
                AsyncImage(
                    model = homeStore[page].picture,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.test_dr),
                    contentScale = ContentScale.Crop, modifier = Modifier.height(182.dp)
                )
                Column(Modifier.padding(start = 25.dp, top = 14.dp)) {
                    if (homeStore[page].is_new) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(colorResource(id = R.color.orange_app))
                                .size(27.dp), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "New", color = colorResource(id = R.color.white), style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.sf_pro_d)),
                                    fontWeight = FontWeight(700),
                                    fontSize = 10.sp
                                )
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.size(27.dp))
                    }
                    Text(
                        text = homeStore[page].title,
                        modifier = Modifier.padding(top = 10.dp),
                        color = colorResource(
                            id = R.color.white
                        ), style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = homeStore[page].subtitle,
                        color = colorResource(
                            id = R.color.white
                        ), style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sf_pro_d)),
                            fontWeight = FontWeight(400),
                            fontSize = 11.sp, letterSpacing = (-0.03).sp, lineHeight = 13.13.sp
                        )
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .width(98.dp)
                            .height(23.dp)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(Color.White), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Buy now!", style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.sf_pro_d)),
                                fontWeight = FontWeight(700),
                                fontSize = 11.sp, letterSpacing = (-0.03).sp, lineHeight = 13.13.sp,
                                color = MaterialTheme.colors.secondary
                            )
                        )
                    }

                }

            }
        }
    }


    @Composable
    private fun bestSellers(list: List<BestSeller>) {

        for (i in 0 until list.size step 2) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 13.dp)
//                .padding(horizontal = 10.dp, vertical = 13.dp)
                    .fillMaxWidth()
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                    bestSellerItem(list[i])
                }
                if (i <= list.size - 2) Box(modifier = Modifier.fillMaxWidth()) {
                    bestSellerItem(list[i + 1])
                }

            }
        }
    }


    @Composable
    private fun bestSellerItem(item: BestSeller) {

        Box(
            modifier = Modifier
                .padding(horizontal = 7.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.white))
                .fillMaxSize()
                .clickable {
                    findNavController().navigate(R.id.navigation_details)
                }, contentAlignment = Alignment.TopEnd
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = item.picture,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.ic_test_bestsellers),
                    modifier = Modifier.height(168.dp),
                    contentScale = ContentScale.FillBounds
                )
                Row(
                    modifier = Modifier.padding(start = 21.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "$" + item.price_without_discount.toString(), style = MaterialTheme.typography.body1)
                    Text(
                        text = "$" + item.discount_price.toString(),
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(start = 7.dp)
                    )
                }
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(start = 21.dp, bottom = 15.dp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 11.dp, end = 13.dp)
                    .shadow(20.dp, CircleShape)
                    .clip(CircleShape)
                    .size(25.dp)
                    .background(MaterialTheme.colors.primary),
                contentAlignment = Alignment.Center

            ) {
                if (!item.is_favorites) Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = null,
                    modifier = Modifier.size(11.dp),
                    tint = MaterialTheme.colors.primaryVariant
                )
                else Icon(
                    painter = painterResource(id = R.drawable.ic_liked),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    private fun Filter(){
        val style1 = MaterialTheme.typography.h1

            Column(modifier = Modifier.padding(horizontal = 44.dp, vertical = 24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Card(shape = RoundedCornerShape(10.dp), backgroundColor = colorResource(id = R.color.darkblue_app), modifier = Modifier.size(37.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_x),
                            contentDescription = null,
                            tint = colorResource(id = R.color.white),
                            modifier = Modifier.padding(13.dp))
                    }
                    Text(text = "Filter options", style = style1)
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = colorResource(id = R.color.orange_app),
                        modifier = Modifier.height(37.dp)
                    ) {
                        Text(text = "Done", modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp), style =style1
                            , color = colorResource(id = R.color.white) )
                    }
                }


                Text(text = "Brand", style = style1, modifier = Modifier.padding(top = 30.dp))
                dropmenu()

                Text(text = "Price", style = style1 , modifier = Modifier.padding(top = 10.dp))



                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .border(1.dp, colorResource(id = R.color.border_color)) ){
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .height(37.dp)
                        .padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "$300 - $500", style = style1, fontWeight = FontWeight(400))
                        Icon(painter = painterResource(id = R.drawable.arrowdown), contentDescription = null , modifier = Modifier
                            .width(16.dp)
                            .height(8.dp))
                    }
                }

                Text(text = "Size", style = style1, modifier = Modifier.padding(top = 10.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .padding(top = 7.dp)
                    .border(1.dp, colorResource(id = R.color.border_color)) ){
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .height(37.dp)
                        .padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "4.5 to 5.5 inches", style = style1, fontWeight = FontWeight(400))
                        Icon(painter = painterResource(id = R.drawable.arrowdown), contentDescription = null , modifier = Modifier
                            .width(16.dp)
                            .height(8.dp))
                    }
                }


            }
        }


    @Composable
    private fun dropmenu(){

        val list = listOf("Apple", "Acer", "Samsung", "OnePlus")

        val style1 = MaterialTheme.typography.h1
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var selected by remember {
            mutableStateOf("Samsung")
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable {
                isExpanded = true
            }
            .border(1.dp, colorResource(id = R.color.border_color)) ){
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .height(37.dp)
                .padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = selected, style = style1, fontWeight = FontWeight(400))
                Icon(painter = painterResource(id = R.drawable.arrowdown), contentDescription = null , modifier = Modifier
                    .width(16.dp)
                    .height(8.dp))

                DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false} ) {
                    list.forEach { label->
                        DropdownMenuItem(onClick = {
                            selected = label
                            isExpanded = false}) {
                            Text(text = label)
                        }
                    }
                }
            }

        }


    }

    @Preview(showBackground = true)
    @Composable
    private fun Previewphoto() {
        val categories = listOf(
            CategoryItem("Phones", R.drawable.phone),
            CategoryItem("Computer", R.drawable.computer),
            CategoryItem("Health", R.drawable.health),
            CategoryItem("Books", R.drawable.books),
            CategoryItem("SSD", R.drawable.phone),
        )
        Phototes(categories)
    }

}














