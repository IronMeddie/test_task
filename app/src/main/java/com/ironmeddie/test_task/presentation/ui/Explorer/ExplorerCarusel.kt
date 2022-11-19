package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.domain.models.HomeStore
import com.ironmeddie.test_task.presentation.ui.activity.Screens

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ExplorerCarusel(homeStore: List<HomeStore>, navController: NavController) {
    HorizontalPager(
        count = homeStore.size, modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 15.dp)
            .fillMaxWidth()
    ) { page ->
        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(horizontal = 5.dp)) {
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
                        .background(Color.White)
                        .clickable { navController.navigate(Screens.Details.route) }, contentAlignment = Alignment.Center
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