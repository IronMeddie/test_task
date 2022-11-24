package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ironmeddie.test_task.R


@Composable
fun ExplorerTopBar(filter: () -> Unit) {
    Row(
        modifier = Modifier
            .height(74.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(start = 35.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = null,
                tint = MaterialTheme.colors.primaryVariant, modifier = Modifier.size(15.dp)
            )
            Text(
                text = "Zihuatanejo, Gro",
                color = colorResource(id = R.color.darkblue_app),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(start = 11.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrowdown),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(10.dp)
                    .height(5.dp),
                tint = colorResource(id = R.color.Grey1)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.filter),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 35.dp)
                .size(15.dp)
                .clickable { filter() },
            tint = colorResource(id = R.color.darkblue_app)
        )

    }
}