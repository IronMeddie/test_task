package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ironmeddie.test_task.domain.models.CategoryItem


@Composable
fun ExplorerCategories(
    categories: List<CategoryItem>,
    onItemChanged: (item: CategoryItem) -> Unit
) {
    var isselected by rememberSaveable { mutableStateOf(0) }
    val listState = rememberLazyListState()
    LazyRow(
        modifier = Modifier.padding(top = 24.dp),
        contentPadding = PaddingValues(horizontal = 12.dp), state = listState,
    ) {
        itemsIndexed(categories, key = { index, item -> item.name }) { i, item ->
            ExplorerCategoryItem(item, i == isselected) { itemNEw ->
                isselected = categories.indexOf(itemNEw)
                onItemChanged(itemNEw)
            }
        }
    }

}