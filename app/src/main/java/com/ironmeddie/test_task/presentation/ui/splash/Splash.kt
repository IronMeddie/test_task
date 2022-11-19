package com.ironmeddie.test_task.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme

@Composable
fun SplashScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .clip(CircleShape)
            .size(132.dp)
            .background(MaterialTheme.colors.primaryVariant)) {

        }
        Text(text = "Ecommerce \n" +
                "Concept ", fontWeight = FontWeight(800), fontSize = 30.sp, style = MaterialTheme.typography.h2,  lineHeight = 33.sp, color = MaterialTheme.colors.primary  ,modifier = Modifier.padding(start = 109.dp)
        )

        
    }

}

@Preview(showBackground = true)
@Composable
fun previewSplash(){
    MyTheme() {
        SplashScreen()
    }
}
