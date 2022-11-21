package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ironmeddie.domain.models.CategoryItem


@Composable
fun ExplorerCategories(
    categories: List<CategoryItem>, selected: String,
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