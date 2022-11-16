package com.ironmeddie.test_task.presentation.ui.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.domain.models.Details
import com.ironmeddie.test_task.presentation.ui.theme.RatingYellow

@Composable
fun DetailsInfo(details: Details) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.primary)
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .padding(top = 28.dp, start = 37.dp, end = 37.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = details.title,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.h1
                )
                Card(
                    modifier = Modifier
                        .width(37.dp)
                        .height(33.dp),
                    backgroundColor = MaterialTheme.colors.secondary,
                    shape = MaterialTheme.shapes.small
                ) {
                    Icon(
                        painter = if (details.isFavorites) painterResource(id = R.drawable.ic_liked) else painterResource(
                            id = R.drawable.ic_like
                        ),
                        contentDescription = null,
                        tint = if (details.isFavorites) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(13.dp)
                            .padding(horizontal = 11.dp, vertical = 10.dp)
                    )
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
            DetailsTabs(details)


        }

    }
}