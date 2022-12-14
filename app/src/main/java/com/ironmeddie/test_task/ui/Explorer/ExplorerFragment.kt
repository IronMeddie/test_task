package com.ironmeddie.test_task.ui.Explorer


import android.content.ClipData.Item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentExplorerBinding
import com.ironmeddie.test_task.domain.models.CategoryItem
import com.ironmeddie.test_task.domain.models.HomeStore
import com.ironmeddie.test_task.ui.theme.MyAppTextFieldColors
import com.ironmeddie.test_task.ui.theme.MyTheme
import com.ironmeddie.test_task.ui.theme.Typography
import kotlin.math.absoluteValue


class ExplorerFragment : Fragment() {

    private var _binding: FragmentExplorerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplorerBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val categories = listOf(
                    CategoryItem("Phones", R.drawable.phone),
                    CategoryItem("Computer", R.drawable.computer),
                    CategoryItem("Health", R.drawable.health),
                    CategoryItem("Books", R.drawable.books),
                    CategoryItem("SSD", R.drawable.phone),
                    CategoryItem("nol", R.drawable.phone),
                    CategoryItem("fdgfdgs", R.drawable.phone),
                    CategoryItem("SSfsgfsD", R.drawable.phone),
                )
                Phototes(categories)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    @Composable
    private fun Phototes(categories: List<CategoryItem>) {

        Scaffold(
            topBar = { topBar() },
            backgroundColor = colorResource(id = R.color.GreyBackground)
        )
        { paddings ->
            LazyColumn(modifier = Modifier.padding(paddings)) {
                item {
                    Headers(
                        name = "Select Category",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp)
                            .fillMaxWidth()
                    )
                }
                item {
                    verticalCategoryList(categories){

                    }
                }
                item {
                    SearhPanel()
                }
                item {
                    Headers(
                        "Hot sales",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp, top = 24.dp)
                            .fillMaxWidth()
                    )
                }
                item {
                    val list = listOf(
                        HomeStore(1, true, true,"https://img.ibxk.com.br/2020/09/23/23104013057475.jpg?w=1120&h=420&mode=crop&scale=bot","S??per. Mega. R??pido.","Iphone 12"),
                        HomeStore(2, true, false,"https://cdn-2.tstatic.net/kupang/foto/bank/images/pre-order-samsung-galaxy-a71-laris-manis-inilah-rekomendasi-ponsel-harga-rp-6-jutaan.jpg","S??per. Mega. R??pido.","Samsung Galaxy A71"))
                    Carusel(list)
                }
                item {
                    Headers(
                        "Best seller",
                        Modifier
                            .padding(start = 17.dp, end = 33.dp)
                            .fillMaxWidth()
                    )
                }
                item { 
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.bottom_menu_height)))
                }
            }
        }

    }


    @Composable
    fun Headers(name: String, modifier: Modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                color = colorResource(id = R.color.darkblue_app),
                fontSize = 25.sp,
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.mark_pro_heavy))
            )
            Text(
                text = "view all",
                color = colorResource(id = R.color.orange_app),
                fontSize = 15.sp,
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(Font(R.font.mark_pro_heavy))
            )
        }
    }

    @Composable
    private fun verticalCategoryList(categories: List<CategoryItem>, onItemChanged: (item: CategoryItem) -> Unit) {
        var isselected by remember { mutableStateOf(0) }
        LazyRow(
            modifier = Modifier.padding(top = 24.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            itemsIndexed(categories) { i, item ->
                kategotyItem(item, i == isselected) { itemNEw ->
                    isselected = categories.indexOf(itemNEw)
                    onItemChanged(itemNEw)
                }
            }
        }
    }


    @Composable
    private fun kategotyItem(
        item: CategoryItem,
        isselected: Boolean,
        choose: (item: CategoryItem) -> Unit
    ) {
        val initialColor = colorResource(id = R.color.white)
        val targetColor = colorResource(id = R.color.orange_app)

        val animatecolor = remember { Animatable(initialColor) }
        if (isselected){
        LaunchedEffect(animatecolor) {
                animatecolor.animateTo(
                    targetValue = targetColor
                    , animationSpec = tween(500)
                )
            }
        }else LaunchedEffect(animatecolor) {
            animatecolor.animateTo(
                targetValue = initialColor
                ,
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(horizontal = 11.dp)
            .clickable {
                choose(item)
            }) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(71.dp)
                    .shadow(elevation = 10.dp)
                    .background(
                        color = animatecolor.asState().value

//                        if (isselected) animateColorAsState(targetValue = colorResource(id = R.color.orange_app)).value else animateColorAsState(targetValue = colorResource(id = R.color.white)).value
//                        if (isselected) colorResource(id = R.color.orange_app) else colorResource(
//                            id = R.color.white
//                        )
                    ), contentAlignment = Alignment.Center
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
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(Font(R.font.mark_pro_heavy)),
                modifier = Modifier.padding(top = 7.dp)
            )
        }
    }


    @Composable
    private fun topBar() {
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
                    tint = colorResource(
                        id = R.color.orange_app
                    ), modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "Zihuatanejo, Gro",
                    color = colorResource(id = R.color.darkblue_app),
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(R.font.mark_pro_heavy)),
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
                    .size(15.dp),
                tint = colorResource(id = R.color.darkblue_app)
            )

        }
    }


    @Composable
    fun SearhPanel() {
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
            OutlinedTextField(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(0.8f),
                shape = RoundedCornerShape(50.dp),
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
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily(Font(R.font.mark_pro_heavy))
                    )

                },
                value = search,
                onValueChange = { search = it },
            )
            Box(modifier = Modifier.padding(top = 8.dp)) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                        .background(colorResource(id = R.color.orange_app)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.group_1),
                        contentDescription = null,
                        modifier = Modifier.size(23.dp),
                        tint = colorResource(
                            id = R.color.white
                        )
                    )
                }
            }

        }

//        TextField(value = search,
//            onValueChange = { search = it },
//            modifier = Modifier
//                .padding(start = 32.dp)
//                .width(300.dp),
//            shape = RoundedCornerShape(50.dp),
//            colors = MyAppTextFieldColors(), leadingIcon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_search),
//                    contentDescription = "search_icon_input",
//                    tint = colorResource(id = R.color.orange_app),
//                    modifier = Modifier
//                        .padding(horizontal = 24.dp)
//                )
//            },
//            label = {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//
//                    Text(
//                        text = "Search",
//                        color = colorResource(id = R.color.textColorSearch),
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight(400),
//                        fontFamily = FontFamily(font)
//                    )
//                }})


    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun Carusel(homeStore: List<HomeStore>) {

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
                    contentScale = ContentScale.Crop, modifier = Modifier.height(182.dp )
                )
                Column(Modifier.padding(start = 25.dp, top = 14.dp)) {
                    if (homeStore[page].is_new){
                        Box(modifier = Modifier
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.orange_app))
                            .size(27.dp) , contentAlignment = Alignment.Center) {
                            Text("New", color = colorResource(id = R.color.white), style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.sf_pro_d)),
                                fontWeight = FontWeight(700),
                                fontSize = 10.sp
                            ))
                        }
                    }else{
                        Spacer(modifier = Modifier.size(27.dp))
                    }
                    Text(
                        text = homeStore[page].title,
                        modifier = Modifier.padding(top =10.dp),
                        color = colorResource(
                            id = R.color.white
                        ), style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sf_pro_d)),
                            fontWeight = FontWeight(700),
                            fontSize = 25.sp, letterSpacing = (-0.01).sp, lineHeight = 29.83.sp
                        ))
                    Text(
                        text = homeStore[page].subtitle,
                        color = colorResource(
                            id = R.color.white
                        ),  style = TextStyle(
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
                        Text(text = "Buy now!", style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sf_pro_d)),
                            fontWeight = FontWeight(700),
                            fontSize = 11.sp, letterSpacing = (-0.03).sp, lineHeight = 13.13.sp
                        ))
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
        MyTheme() {
            Phototes(categories)
        }

    }

}














