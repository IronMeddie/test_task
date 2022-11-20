package com.ironmeddie.test_task.presentation.ui.base

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReconnectButton(reconnect: () -> Unit){
    Box(modifier = Modifier.fillMaxWidth().height(182.dp), contentAlignment = Alignment.Center){
        Column() {
            Text(text = "Connection failure")
            Button(onClick = { reconnect() }) {
                Text(text = "try to connect")
            }
        }

    }
}