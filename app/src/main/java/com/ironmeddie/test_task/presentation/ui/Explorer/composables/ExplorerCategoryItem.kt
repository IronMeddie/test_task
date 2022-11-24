package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ironmeddie.domain.models.CategoryItem
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme
import com.ironmeddie.test_task.presentation.ui.theme.Shadow


@Composable
fun ExplorerCategoryItem(
    item: CategoryItem,
    isselected: Boolean,
    choose: (item: CategoryItem) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .padding(horizontal = 11.dp)
        .clickable {
            choose(item)
        }) {
        Box(
            modifier = Modifier
                .shadow(20.dp, CircleShape, spotColor = Shadow)
                .clip(CircleShape)
                .size(71.dp)
                .background(color = if (isselected) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                tint = if (isselected) colorResource(R.color.white) else colorResource(id = R.color.GreyIcons),
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = item.name,
            color = if (isselected) colorResource(id = R.color.orange_app) else colorResource(
                id = R.color.darkblue_app
            ),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 7.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewExplorerCategoryItem() {
    MyTheme {
        ExplorerCategoryItem(CategoryItem("Phones",R.drawable.phone),false){

        }
    }

}