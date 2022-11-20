package com.ironmeddie.test_task.presentation.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ironmeddie.domain.models.Cart
import com.ironmeddie.test_task.presentation.ui.theme.White20P


@Composable
fun CartList(cart: Cart) {


    Card(
        backgroundColor = MaterialTheme.colors.secondary, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column() {
            Column(Modifier.padding(vertical = 34.dp)) {
                cart.basket.forEach {
                    CartItem(it)
                }
            }

            Column(Modifier.padding(vertical = 44.dp)) {
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 3.dp)
                        .background(White20P)
                        .height(2.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 35.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total",
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text = "$${cart.total} us",
                        style = MaterialTheme.typography.body1,
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 35.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Delivery",
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text = cart.delivery,
                        style = MaterialTheme.typography.body1,
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 14.dp)
                        .background(White20P)
                        .height(2.dp)
                        .fillMaxWidth()

                )

                Box(modifier = Modifier
                    .padding(top = 14.dp, start = 44.dp, end = 44.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colors.primaryVariant)
                    .clickable { }) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 45.dp, vertical = 14.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Checkout",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primary,
                            fontSize = 20.sp
                        )
                    }

                }

            }
        }
    }
}


