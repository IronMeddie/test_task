package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.theme.Grey3


@Composable
fun ExplorerFilterDropMenu() {

    val list = listOf("Apple", "Acer", "Samsung", "OnePlus")
    val style1 = MaterialTheme.typography.h1
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("Samsung")
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 7.dp)
        .clip(RoundedCornerShape(5.dp))
        .clickable {
            isExpanded = true
        }
        .border(1.dp, colorResource(id = R.color.border_color))) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .height(37.dp)
                .padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selected, style = style1, fontWeight = FontWeight(400))
            Icon(
                painter = painterResource(id = R.drawable.arrowdown),
                contentDescription = null,
                modifier = Modifier
                    .width(16.dp)
                    .height(8.dp), tint = Grey3
            )
            DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                list.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selected = label
                        isExpanded = false
                    }) {
                        Text(text = label, style = style1)
                    }
                }
            }
        }

    }


}