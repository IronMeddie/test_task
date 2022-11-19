package com.ironmeddie.test_task.presentation.ui.Explorer

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme

@Composable
fun ExplorerBottomMenu(navController: NavController,onItem :(route: String) -> Unit){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val items = listOf("Explorer" ,"Cart", "Favorite" , "Profile")
    val icons = listOf(R.drawable.ic_circle_12 ,R.drawable.ic_shop, R.drawable.ic_like , R.drawable.ic_group)

    BottomNavigation(
        Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .height(72.dp), backgroundColor = MaterialTheme.colors.secondary) {
//        val selected by remember { mutableStateOf(navController.currentDestination?.route) }
        val selected = navController.currentDestination?.route

        
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            , horizontalArrangement = Arrangement.SpaceEvenly) {
            items.forEachIndexed { i, it->
                Row(
                    Modifier
                        .fillMaxHeight()
                        .clickable {
//                            selected = it
                            onItem(it)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = icons[i]),
                        contentDescription = null,
                        modifier = if (i == 0) Modifier.size(8.dp) else Modifier.size(17.dp),
                        tint = MaterialTheme.colors.primary
                    )
                    AnimatedVisibility(visible = selected == it,
                        enter = fadeIn() + expandHorizontally(),
                        exit = shrinkHorizontally()
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primary,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(start = 7.dp)
                        )
                    }

                }
            }
        }


    }

}

@Preview
@Composable
private fun PreviewBottomMenu(){
    MyTheme() {
        val navController= rememberNavController()
        ExplorerBottomMenu(navController){
            navController.navigate(it) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}