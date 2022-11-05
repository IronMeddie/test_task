package com.ironmeddie.test_task.ui.Explorer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentExplorerBinding
import com.ironmeddie.test_task.domain.models.CategoryItem


class ExplorerFragment : Fragment() {

    private var _binding: FragmentExplorerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplorerBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val categories = listOf(
                    CategoryItem("Phones", R.drawable.phone),
                    CategoryItem("Computer", R.drawable.computer),
                    CategoryItem("Health", R.drawable.health),
                    CategoryItem("Books", R.drawable.books),
                    CategoryItem("SSD", R.drawable.phone),
                )
                Phototes(categories)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    val font = Font(R.font.mark_pro_heavy)


    @Composable
    private fun Phototes(categories: List<CategoryItem>) {
        Scaffold(
            topBar = { topBar() },
            backgroundColor = colorResource(id = R.color.GreyBackground)
        )
        { paddings ->
            LazyColumn(modifier = Modifier.padding(paddings)) {
                item {
                    Row(
                        modifier = Modifier
                            .padding(start = 17.dp, end = 33.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Select Category",
                            color = colorResource(id = R.color.darkblue_app),
                            fontSize = 25.sp,
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily(font)
                        )
                        Text(
                            text = "view all",
                            color = colorResource(id = R.color.orange_app),
                            fontSize = 15.sp,
                            fontWeight = FontWeight(500),
                            fontFamily = FontFamily(font)
                        )
                    }
                }
                item {
                    verticalCategoryList(categories)
                }
                item {
                    SearhPanel()
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(start = 17.dp, end = 33.dp, top = 24.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Hot sales",
                            color = colorResource(id = R.color.darkblue_app),
                            fontSize = 25.sp,
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily(font)
                        )
                        Text(
                            text = "view all",
                            color = colorResource(id = R.color.orange_app),
                            fontSize = 15.sp,
                            fontWeight = FontWeight(500),
                            fontFamily = FontFamily(font)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun verticalCategoryList(categories: List<CategoryItem>) {
        var isselected by remember { mutableStateOf(0) }
        LazyRow(
            modifier = Modifier.padding(top = 24.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            itemsIndexed(categories) { i, item ->
                kategotyItem(item, i == isselected) { itemNEw ->
                    isselected = categories.indexOf(itemNEw)
                }
            }
        }
    }

    @Composable
    private fun kategotyItem(
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
                    .clip(CircleShape)
                    .size(71.dp)
                    .shadow(elevation = 10.dp)
                    .background(
                        if (isselected) colorResource(id = R.color.orange_app) else colorResource(
                            id = R.color.white
                        )
                    ), contentAlignment = Alignment.Center
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
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(font),
                modifier = Modifier.padding(top = 7.dp)
            )
        }
    }


    @Composable
    private fun topBar() {
        Row(
            modifier = Modifier
                .height(74.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.padding(start = 5.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = null,
                    tint = colorResource(
                        id = R.color.orange_app
                    ), modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "Zihuatanejo, Gro",
                    color = colorResource(id = R.color.darkblue_app),
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(font),
                    modifier = Modifier.padding(start = 11.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .width(10.dp)
                        .height(5.dp),
                    tint = colorResource(id = R.color.Grey1)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 35.dp)
                    .size(15.dp),
                tint = colorResource(id = R.color.darkblue_app)
            )

        }
    }

    @Composable
    fun MyAppTextFieldColors(
        textColor: Color = colorResource(id = R.color.textColorSearch),
        disabledTextColor: Color = colorResource(R.color.white),
        backgroundColor: Color = colorResource(R.color.white),
        cursorColor: Color = colorResource(R.color.white),
        errorCursorColor: Color = colorResource(R.color.white),
        placeholderColor: Color = colorResource(R.color.white),
        disabledPlaceholderColor: Color = colorResource(R.color.white),
        focusedBorderColor: Color = colorResource(R.color.transparent),
        unfocusedBorderColor: Color = colorResource(R.color.transparent)
    ) = TextFieldDefaults.textFieldColors(
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        backgroundColor = backgroundColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        placeholderColor = placeholderColor,
        disabledPlaceholderColor = disabledPlaceholderColor,
        focusedIndicatorColor = focusedBorderColor,
        unfocusedIndicatorColor = unfocusedBorderColor

    )


    @Composable
    fun SearhPanel() {
        var search by remember {
            mutableStateOf("")
        }
        Row( verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 34.dp, start = 32.dp, end = 32.dp )
            , horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                modifier = Modifier
                    .height(55.dp).fillMaxWidth(0.8f)
                    ,
                shape = RoundedCornerShape(50.dp),
                colors = MyAppTextFieldColors(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "search_icon_input",
                        tint = colorResource(id = R.color.orange_app),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                    )
                },
                label = {
                    Text(
                        text = "Search",
                        color = colorResource(id = R.color.textColorSearch),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily(font)
                    )

                },
                value = search,
                onValueChange = { search = it },
            )
            Box(modifier = Modifier.padding(top = 8.dp)) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                        .background(colorResource(id = R.color.orange_app)),
                    contentAlignment = Alignment.Center
                ) {
                Icon(painter = painterResource(id = R.drawable.group_1), contentDescription = null, modifier = Modifier.size(23.dp), tint = colorResource(
                    id = R.color.white
                ))
                }
            }

        }

//        TextField(value = search,
//            onValueChange = { search = it },
//            modifier = Modifier
//                .padding(start = 32.dp)
//                .width(300.dp),
//            shape = RoundedCornerShape(50.dp),
//            colors = MyAppTextFieldColors(), leadingIcon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_search),
//                    contentDescription = "search_icon_input",
//                    tint = colorResource(id = R.color.orange_app),
//                    modifier = Modifier
//                        .padding(horizontal = 24.dp)
//                )
//            },
//            label = {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//
//                    Text(
//                        text = "Search",
//                        color = colorResource(id = R.color.textColorSearch),
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight(400),
//                        fontFamily = FontFamily(font)
//                    )
//                }})


    }


    @Preview(showBackground = true)
    @Composable
    private fun Previewphoto() {
        val categories = listOf(
            CategoryItem("Phones", R.drawable.phone),
            CategoryItem("Computer", R.drawable.computer),
            CategoryItem("Health", R.drawable.health),
            CategoryItem("Books", R.drawable.books),
            CategoryItem("SSD", R.drawable.phone),
        )
        Phototes(categories)
    }


}













