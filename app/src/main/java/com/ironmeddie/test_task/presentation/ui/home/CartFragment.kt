package com.ironmeddie.test_task.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentCartBinding
import com.ironmeddie.test_task.presentation.MainActivity
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    val cartViewModel by viewModels<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).hideBottomMenu()

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MyTheme {
//                    val details = viewmodel.details.collectAsState()
                    CartScreen()

                }
            }
        }



        return root
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewCart(){
        MyTheme {
            CartScreen()
        }
    }


    @Composable
    fun CartScreen(){
        Scaffold(topBar = {topBar(R.drawable.ic_arrow_back, R.drawable.location)}) {
            LazyColumn(modifier = Modifier
                .padding(it)
                .fillMaxSize()){

            }
        }
    }


    @Composable
    private fun topBar(iconStart: Int, iconEnd : Int){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Card(shape = RoundedCornerShape(10.dp), backgroundColor = colorResource(id = R.color.darkblue_app), modifier = Modifier.size(37.dp). clickable {
                findNavController().popBackStack()
            }) {
                Icon(
                    painter = painterResource(id = iconStart),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(13.dp))
            }
            Text(text = "Product Details", style = MaterialTheme.typography.h1)
            Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = colorResource(id = R.color.orange_app),
                modifier = Modifier.height(37.dp).
                clickable {
                    findNavController().navigate(R.id.navigation_cart)
                }
            ) {
                Icon(painter = painterResource(id = iconEnd), contentDescription = null, tint = MaterialTheme.colors.primary, modifier = Modifier.padding(11.dp) )
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}