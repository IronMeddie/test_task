package com.ironmeddie.test_task.presentation.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.ironmeddie.test_task.R
import com.ironmeddie.domain.models.Details
import com.ironmeddie.test_task.presentation.ui.theme.GreyIcons
import com.ironmeddie.test_task.presentation.ui.theme.GreyText
import com.ironmeddie.test_task.presentation.ui.theme.Transparent

@Composable
fun Shop(details: com.ironmeddie.domain.models.Details) {
    var selectedCapacity by remember { mutableStateOf(0) }
    var selectedColor by remember { mutableStateOf(0) }



    Column(modifier = Modifier.padding(horizontal = 30.dp, vertical = 29.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(50.dp).width(64.dp)
            ) {
                Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cpu),
                        contentDescription = null,
                        tint = GreyIcons
                    )
                }
                Text(text = details.CPU, style = MaterialTheme.typography.subtitle2)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(50.dp).width(64.dp)
            ) {
                Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = null,
                        tint = GreyIcons
                    )
                }
                Text(text = details.camera, style = MaterialTheme.typography.subtitle2)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(50.dp).width(64.dp)
            ) {
                Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ssd),
                        contentDescription = null,
                        tint = GreyIcons,
                        modifier = Modifier
                            .width(28.dp)
                            .height(21.dp)
                    )
                }
                Text(text = details.ssd, style = MaterialTheme.typography.subtitle2)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(50.dp).width(64.dp)
            ) {
                Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_hdd),
                        contentDescription = null,
                        tint = GreyIcons
                    )
                }
                Text(text = details.sd, style = MaterialTheme.typography.subtitle2)
            }
        }

        Text(text = "Select color and capacity", modifier = Modifier.padding(top = 29.dp))
        Row(Modifier.padding(top = 15.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                details.color.forEach {
                    Box(modifier = Modifier
                        .padding(end = 18.dp)
                        .clip(CircleShape)
                        .size(39.dp)
                        .background(Color(it.toColorInt()))
                        .clickable {
                            selectedColor = details.color.indexOf(it)
                        }, contentAlignment = Alignment.Center)
                    {
                        if (selectedColor == details.color.indexOf(it)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_choice),
                                contentDescription = null
                            )
                        }
                    }
                }


                val style = TextStyle(
                    color = GreyText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = FontFamily(Font(R.font.mark_pro_heavy)),
                    letterSpacing = (-0.03).sp
                )


                details.capacity.forEach {
                    Box(modifier = Modifier
                        .padding(start = 20.dp)
                        .width(72.dp)
                        .height(30.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(if (selectedCapacity == details.capacity.indexOf(it)) MaterialTheme.colors.primaryVariant else Transparent)
                        .clickable {
                            selectedCapacity = details.capacity.indexOf(it)
                        }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it + " GB",
                            style = style,
                            color = if (selectedCapacity == details.capacity.indexOf(it)) MaterialTheme.colors.primary else GreyText
                        )
                    }
                }
            }
        }




        Box(modifier = Modifier
            .padding(top = 27.dp)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.primaryVariant)
            .clickable { }) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .padding(horizontal = 45.dp, vertical = 14.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Add to Cart",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    fontSize = 20.sp
                )
                Text(
                    text = "$${details.price}",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    fontSize = 20.sp
                )
            }

        }

    }



}