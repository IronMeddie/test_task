package com.ironmeddie.test_task.presentation.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.util.lerp
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentDetailsBinding
import com.ironmeddie.test_task.domain.models.Details
import com.ironmeddie.test_task.presentation.MainActivity
import com.ironmeddie.test_task.presentation.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewmodel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as MainActivity).hideBottomMenu()

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MyTheme {
                    val details = viewmodel.details.collectAsState()
                    Scaffold(topBar = {topBar(R.drawable.ic_arrow_back, R.drawable.ic_shop)}) {
                        LazyColumn(modifier = Modifier
                            .padding(it)
                            .fillMaxSize()){
                            item {
                                HorizontalPagerWithOffsetTransition(images = details.value.images)
                            }
                            item {
                                Details(details.value)
                            }
                        }
                    }

                }
            }
        }
        return view
    }


    @Composable
    fun Details(details: Details){
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.primary)) {
            Column() {
                Row(modifier = Modifier
                    .padding(top = 28.dp, start = 37.dp, end = 37.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = details.title, fontSize = 24.sp, style = MaterialTheme.typography.h1)
                    Card(modifier = Modifier
                        .width(37.dp)
                        .height(33.dp), backgroundColor = MaterialTheme.colors.secondary, shape = MaterialTheme.shapes.small) {
                        Icon(painter = if (details.isFavorites) painterResource(id = R.drawable.ic_liked) else painterResource(id = R.drawable.ic_like), contentDescription = null, tint = if (details.isFavorites) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary ,modifier = Modifier
                            .size(13.dp)
                            .padding(horizontal = 11.dp, vertical = 10.dp))
                    }
                }
                Box(modifier = Modifier.padding(horizontal = 38.dp, vertical = 7.dp)) {
                    RatingBar(value = details.rating.toFloat(),
                        config = RatingBarConfig()
                            .activeColor(RatingYellow)
                            .inactiveBorderColor(MaterialTheme.colors.primaryVariant)
                            .size(18.dp)
                            .style(RatingBarStyle.HighLighted),
                        onValueChange = {

                        },
                        onRatingChanged = {
                            Log.d("TAG", "onRatingChanged: $it")
                        }
                    )
                }
                tabs(details)


            }

        }
    }



    @Composable
    private fun topBar(iconStart: Int, iconEnd : Int){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Card(shape = RoundedCornerShape(10.dp), backgroundColor = colorResource(id = R.color.darkblue_app), modifier = Modifier.size(37.dp). clickable {
                findNavController().popBackStack()
            }) {
                Icon(
                    painter = painterResource(id = iconStart),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(13.dp))
            }
            Text(text = "Product Details", style = MaterialTheme.typography.h1)
            Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = colorResource(id = R.color.orange_app),
                modifier = Modifier.height(37.dp).
                        clickable {
                            findNavController().navigate(R.id.navigation_cart)
                        }
            ) {
                Icon(painter = painterResource(id = iconEnd), contentDescription = null, tint = MaterialTheme.colors.primary, modifier = Modifier.padding(11.dp) )
            }
        }
    }


    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun HorizontalPagerWithOffsetTransition(modifier: Modifier = Modifier, images: List<String>) {
        HorizontalPager(
            count = images.size, modifier = Modifier
                .fillMaxWidth(), contentPadding = PaddingValues( start = 45.dp, end = 45.dp, top = 37.dp, bottom = 14.dp)
        ) { page ->
            Card(shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.83f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .shadow(20.dp, RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .aspectRatio(1f)

            ) {
                    AsyncImage(model = images[page], contentDescription = null, modifier = Modifier
                        .padding(16.dp)
                        .offset {
                            val pageOffset =
                                this@HorizontalPager.calculateCurrentOffsetForPage(page)
                            IntOffset(
                                x = (36.dp * pageOffset).roundToPx(),
                                y = 0
                            )
                        })

            }
        }
    }




    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun tabs(details: Details){
        val pages = listOf("Shop","Details", "Features")
         val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.padding(horizontal = 27.dp),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .clip(RoundedCornerShape(3.dp)),
                    height = 3.dp,
                    color = MaterialTheme.colors.primaryVariant
                )
            }
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(title,
                            fontSize =
//                            20.sp
                            17.sp
                            , style = if(pagerState.currentPage == index) MaterialTheme.typography.body1 else MaterialTheme.typography.subtitle1,
                        color =if(pagerState.currentPage == index) MaterialTheme.colors.secondary else inactiveTabs )
                           },
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch {
                        pagerState.animateScrollToPage(index)
                    } },


                )
            }
        }
        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->

            when(page){
                0 -> Shop(details = details)
                1 -> Text(text = "Details", modifier = Modifier.fillMaxSize())
                2 -> Text(text = "Features", modifier = Modifier.fillMaxSize())
            }


        }
    }

    @Composable
    fun Shop(details: Details){
        var selectedCapacity by remember { mutableStateOf(0) }
        var selectedColor by remember { mutableStateOf(0) }



        Column(modifier = Modifier.padding(horizontal = 30.dp, vertical = 29.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(50.dp)) {
                    Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                        Icon(painter = painterResource(id = R.drawable.ic_cpu), contentDescription = null , tint = GreyIcons)
                    }
                    Text(text = details.CPU, style = MaterialTheme.typography.subtitle2)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(50.dp)) {
                    Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                            tint = GreyIcons
                        )
                    }
                    Text(text = details.camera, style = MaterialTheme.typography.subtitle2)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(50.dp)) {
                    Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_ssd),
                            contentDescription = null,
                            tint = GreyIcons,
                            modifier = Modifier
                                .width(28.dp)
                                .height(21.dp)
                        )
                    }
                    Text(text = details.ssd, style = MaterialTheme.typography.subtitle2)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(50.dp)) {
                    Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_hdd),
                            contentDescription = null,
                            tint = GreyIcons
                        )
                    }
                    Text(text = details.sd, style = MaterialTheme.typography.subtitle2 )
                }
            }

            Text(text = "Select color and capacity", modifier = Modifier.padding(top = 29.dp))
            Row(Modifier.padding(top = 15.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    details.color.forEach {
                        Box(modifier = Modifier
                            .padding(end = 18.dp)
                            .clip(CircleShape)
                            .size(39.dp)
                            .background(Color(it.toColorInt()))
                            .clickable {
                                selectedColor = details.color.indexOf(it)
                            } , contentAlignment = Alignment.Center)
                        {
                            if (selectedColor == details.color.indexOf(it)){
                                Icon(painter = painterResource(id = R.drawable.ic_choice), contentDescription = null)
                            }
                        }
                    }



                    val style = TextStyle(
                        color = GreyText,
                        fontSize = 13.sp,
                        fontWeight = FontWeight(700),
                        fontFamily = FontFamily(Font(R.font.mark_pro_heavy)),
                        letterSpacing = (-0.03).sp
                    )


                    details.capacity.forEach {
                        Box(modifier = Modifier
                            .padding(start = 20.dp)
                            .width(72.dp)
                            .height(30.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(if (selectedCapacity == details.capacity.indexOf(it)) MaterialTheme.colors.primaryVariant else Transparent)
                            .clickable {
                                selectedCapacity = details.capacity.indexOf(it)
                            }, contentAlignment = Alignment.Center){
                            Text(text = it + " GB", style = style, color = if (selectedCapacity == details.capacity.indexOf(it)) MaterialTheme.colors.primary else GreyText )
                        }
                    }
                }
            }




            Box(modifier = Modifier
                .padding(top = 27.dp)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colors.primaryVariant)
                .clickable { }) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .padding(horizontal = 45.dp, vertical = 14.dp)
                    .fillMaxWidth()
                ) {
                    Text(text = "Add to Cart", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.primary, fontSize = 20.sp)
                    Text(text = "$${details.price}", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.primary, fontSize = 20.sp)
                }

            }

        }
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}