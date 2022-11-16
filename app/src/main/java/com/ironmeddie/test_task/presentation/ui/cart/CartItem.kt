package com.ironmeddie.test_task.presentation.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.domain.models.Basket
import com.ironmeddie.test_task.presentation.ui.theme.CartCountBack
import com.ironmeddie.test_task.presentation.ui.theme.CartIconBack

@Composable
fun CartItem(item: Basket) {
    var count by remember { mutableStateOf(1) }
    Row(
        Modifier
            .padding(start = 33.dp, end = 33.dp, top = 46.dp)
            .height(89.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = item.images,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.6f)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
                fontSize = 20.sp
            )
            Text(
                text = "$" + item.price.toString(),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primaryVariant
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .clip(RoundedCornerShape(26.dp))
                .height(68.dp)
                .width(26.dp)
                .background(CartCountBack), verticalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier
                    .size(16.dp)
                    .clickable { if (count > 1) count-- },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_minus),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
            Text(
                text = count.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Box(
                Modifier
                    .size(16.dp)
                    .clickable { count++ }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = null,
            tint = CartIconBack
        )
    }
}