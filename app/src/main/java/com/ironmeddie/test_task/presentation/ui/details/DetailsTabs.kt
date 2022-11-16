package com.ironmeddie.test_task.presentation.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.ironmeddie.test_task.domain.models.Details
import com.ironmeddie.test_task.presentation.ui.theme.inactiveTabs
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsTabs(details: Details) {
    val pages = listOf("Shop", "Details", "Features")
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
                    Text(
                        title,
                        fontSize =
//                            20.sp
                        15.sp,
                        style = if (pagerState.currentPage == index) MaterialTheme.typography.body1 else MaterialTheme.typography.subtitle1,
                        color = if (pagerState.currentPage == index) MaterialTheme.colors.secondary else inactiveTabs
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },


                )
        }
    }
    HorizontalPager(
        count = pages.size,
        state = pagerState,
    ) { page ->

        when (page) {
            0 -> Shop(details = details)
            1 -> Text(text = "Details", modifier = Modifier.fillMaxSize().height(300.dp))
            2 -> Text(text = "Features", modifier = Modifier.fillMaxSize().height(300.dp))
        }


    }
}