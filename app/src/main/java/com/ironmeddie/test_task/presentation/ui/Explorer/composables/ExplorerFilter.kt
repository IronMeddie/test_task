package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.theme.Grey3
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExplorerFilter(bottomSheetState: BottomSheetState) {
    val style1 = MaterialTheme.typography.h1
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.padding(horizontal = 44.dp, vertical = 24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(shape = RoundedCornerShape(10.dp),
                backgroundColor = colorResource(id = R.color.darkblue_app),
                modifier = Modifier
                    .size(37.dp)
                    .clickable {
                        scope.launch {
                            bottomSheetState.collapse()
                        }
                    }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_x),
                    contentDescription = null,
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier.padding(13.dp)
                )
            }
            Text(text = "Filter options", style = style1)
            Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = colorResource(id = R.color.orange_app),
                modifier = Modifier
                    .height(37.dp)
                    .clickable {
                        scope.launch {
                            bottomSheetState.collapse()
                        }
                    }
            ) {
                Text(
                    text = "Done",
                    modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp),
                    style = style1,
                    color = colorResource(id = R.color.white)
                )
            }
        }

        Text(text = "Brand", style = style1, modifier = Modifier.padding(top = 30.dp))
        ExplorerFilterDropMenu()
        Text(text = "Price", style = style1, modifier = Modifier.padding(top = 10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp)
                .clip(RoundedCornerShape(5.dp))
                .border(1.dp, colorResource(id = R.color.border_color))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .height(37.dp)
                    .padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "$300 - $500", style = style1, fontWeight = FontWeight(400))
                Icon(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = null,
                    modifier = Modifier
                        .width(16.dp)
                        .height(8.dp)
                , tint = Grey3
                )
            }
        }

        Text(text = "Size", style = style1, modifier = Modifier.padding(top = 10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .padding(top = 7.dp)
                .border(1.dp, colorResource(id = R.color.border_color))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .height(37.dp)
                    .padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "4.5 to 5.5 inches", style = style1, fontWeight = FontWeight(400))
                Icon(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = null,
                    modifier = Modifier
                        .width(16.dp)
                        .height(8.dp), tint = Grey3
                )
            }
        }


    }
}