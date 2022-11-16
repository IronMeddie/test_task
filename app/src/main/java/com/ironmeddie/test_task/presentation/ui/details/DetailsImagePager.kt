package com.ironmeddie.test_task.presentation.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsImagePager(modifier: Modifier = Modifier, images: List<String>) {
    HorizontalPager(
        count = images.size,
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 45.dp, end = 45.dp, top = 37.dp, bottom = 14.dp)
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