package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.domain.models.BestSeller
import com.ironmeddie.test_task.presentation.ui.activity.Screens


@Composable
fun ExplorerBestSellers(list: List<BestSeller>, navController: NavController) {

    for (i in 0 until list.size step 2) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 13.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                BestSellerItem(list[i], navController)
            }
            if (i <= list.size - 2) Box(modifier = Modifier.fillMaxWidth()) {
                BestSellerItem(list[i + 1], navController)
            }
        }
    }
}


@Composable
private fun BestSellerItem(item: BestSeller, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(horizontal = 7.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.white))
            .fillMaxSize()
            .clickable {
                navController.navigate(Screens.Details.route)
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
                Text(
                    text = "$" + item.price_without_discount.toString(),
                    style = MaterialTheme.typography.body1
                )
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