package com.ironmeddie.test_task.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentDetailsBinding
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme
import kotlin.math.absoluteValue

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

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MyTheme {
                    Scaffold(topBar = {topBar(R.drawable.ic_arrow_back, R.drawable.ic_shop)}) {
                        LazyColumn(modifier = Modifier
                            .padding(it)
                            .fillMaxSize()){
                            val images = listOf(
                                "https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig",
                                "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg"
                            )
                            item {
                                HorizontalPagerWithOffsetTransition(images = images)
                            }
                            item {
                                Details()
                            }
                        }
                    }

                }
            }
        }
        return view
    }

    @Composable
    fun Details(){
        Box(modifier = Modifier
            .fillMaxSize()
            .height(400.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.primary)) {

            Row(modifier = Modifier.padding(top = 28.dp, start = 38.dp)) {
                Text(text = "Galaxy Note 20 Ultra", fontSize = 24.sp, style = MaterialTheme.typography.h1)
                Card() {
                    
                }
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun dsdqasd(){
        MyTheme {
            Scaffold(topBar = {topBar(R.drawable.ic_arrow_back, R.drawable.ic_shop)}) {
                LazyColumn(modifier = Modifier
                    .padding(it)
                    .fillMaxSize()){
                    val images = listOf(
                        "https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig",
                        "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg"
                    )
                    
                    item {
                        HorizontalPagerWithOffsetTransition(images = images)
                    }
                    item {
                        Details()
                    }
                    
                }
            }
        }
    }

    @Composable
    fun topBar(iconStart: Int, iconEnd : Int){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Card(shape = RoundedCornerShape(10.dp), backgroundColor = colorResource(id = R.color.darkblue_app), modifier = Modifier.size(37.dp)) {
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
                modifier = Modifier.height(37.dp)
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
                .fillMaxWidth(), contentPadding = PaddingValues(50.dp)
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






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}