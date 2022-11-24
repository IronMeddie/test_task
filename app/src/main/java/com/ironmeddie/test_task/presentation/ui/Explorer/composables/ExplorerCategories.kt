package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ironmeddie.domain.models.CategoryItem
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.activity.Categories
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme


@Composable
fun ExplorerCategories(
    categories: List<CategoryItem>
       , selected: String,
    onItemChanged: (item: String) -> Unit
) {
    val listState = rememberLazyListState()
    LazyRow(
        modifier = Modifier.padding(top = 24.dp),
        contentPadding = PaddingValues(horizontal = 12.dp), state = listState,
    ) {
        items(categories, key = { item -> item.name }) {  item ->
            ExplorerCategoryItem(item, item.name == selected) { itemNEw ->
                onItemChanged(itemNEw.name)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewexplorerCategories(){
    MyTheme{
        var selected by remember{ mutableStateOf(Categories.CATEGORY_PHONES) }
        ExplorerCategories( categories = listOf(
            CategoryItem(Categories.CATEGORY_PHONES, R.drawable.phone),
            CategoryItem(Categories.CATEGORY_COMPUTER, R.drawable.computer),
            CategoryItem(Categories.CATEGORY_HEALTH, R.drawable.health),
            CategoryItem(Categories.CATEGORY_BOOKS, R.drawable.books),
            CategoryItem(Categories.CATEGORY_SSD, R.drawable.phone),
        ), selected = selected ){
            selected = it
        }
    }
}