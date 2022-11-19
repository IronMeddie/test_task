package com.ironmeddie.test_task.presentation.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.activity.Screens

@Composable
fun DetailsTopBar(iconStart: Int, iconEnd: Int, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = colorResource(id = R.color.darkblue_app),
            modifier = Modifier
                .size(37.dp)
                .clickable {
                    navController.popBackStack()
                }) {
            Icon(
                painter = painterResource(id = iconStart),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(13.dp)
            )
        }
        Text(text = "Product Details", style = MaterialTheme.typography.h1)
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = colorResource(id = R.color.orange_app),
            modifier = Modifier
                .height(37.dp)
                .clickable {
                    navController.navigate(Screens.Cart.route)
                }
        ) {
            Icon(
                painter = painterResource(id = iconEnd),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(11.dp)
            )
        }
    }
}