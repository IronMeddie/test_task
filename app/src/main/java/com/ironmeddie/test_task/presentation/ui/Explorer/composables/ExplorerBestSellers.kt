package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ironmeddie.domain.models.BestSeller
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.activity.Screens
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme


@Preview(showBackground = true)
@Composable
fun ListItemsPreview(){
    val navController = rememberNavController()
    MyTheme() {
        LazyColumn(){
            val list = listOf(
                BestSeller(id = 0),
                BestSeller(id = 1),
                BestSeller(id = 2),
                BestSeller(id = 3),
                BestSeller(id = 4),
                BestSeller(id = 5),
                BestSeller(id = 6),
            )
            for (i in list.indices step 2){
                item(key = {list[i].id}) {
                    Row() {
                        BestSellerItem(list[i],navController, true)
                        if (i+1<list.size) BestSellerItem(list[i+1],navController)
                    }
                }
            }
        }
    }

}


@Composable
fun BestSellerItem(item: BestSeller, navController: NavController, fillHalf: Boolean = false) {
    Box(
        modifier = Modifier
            .padding(horizontal = 7.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.white))
            .fillMaxSize(if (fillHalf)  0.5f else 1f)
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
                contentScale = ContentScale.Crop
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

