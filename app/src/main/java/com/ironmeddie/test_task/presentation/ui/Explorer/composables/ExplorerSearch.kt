package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.theme.MyAppTextFieldColors


@Composable
fun ExplorerSearch() {
    var search by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, start = 32.dp, end = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.large,
            colors = MyAppTextFieldColors(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search_icon_input",
                    tint = colorResource(id = R.color.orange_app),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                )
            },
            label = {
                Text(
                    text = "Search",
                    color = colorResource(id = R.color.textColorSearch),
                    style = MaterialTheme.typography.h4,
                )

            },
            value = search,
            onValueChange = { search = it }, textStyle = MaterialTheme.typography.h4
        )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(45.dp)
                    .background(MaterialTheme.colors.primaryVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.group_1),
                    contentDescription = null,
                    modifier = Modifier.size(23.dp),
                    tint = MaterialTheme.colors.primary
                )
            }


    }

}