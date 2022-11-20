package com.ironmeddie.test_task.presentation.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ironmeddie.data.DataResource
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.base.ReconnectButton

@Composable
fun DetailScreen(viewmodel: DetailsViewModel = hiltViewModel(), navController: NavController){
    val details = viewmodel.details.collectAsState().value
    when(details){
        is DataResource.Failure -> ReconnectButton {
                viewmodel.getInfo()
            }
        is DataResource.Loading -> CircularProgressIndicator()
        is DataResource.Success ->{
            Scaffold(topBar = {
                DetailsTopBar(
                    R.drawable.ic_arrow_back,
                    R.drawable.ic_shop,
                    navController
                )
            }) {
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    item {
                        DetailsImagePager(images = details.value.images)
                    }
                    item {
                        DetailsInfo(details.value)
                    }
                }
            }
        }
    }

}