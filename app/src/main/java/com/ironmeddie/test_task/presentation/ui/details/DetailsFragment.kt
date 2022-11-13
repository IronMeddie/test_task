package com.ironmeddie.test_task.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentDetailsBinding
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewmodel by viewModels<DetailsViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MyTheme {
                    Scaffold(topBar = {topBar(R.drawable.ic_arrow_back, R.drawable.ic_shop)}) {
                        LazyColumn(modifier = Modifier.padding(it).fillMaxSize()){

                        }
                    }

                }
            }
        }
        return view
    }

    @Preview(showBackground = true)
    @Composable
    fun dsdqasd(){
        MyTheme {
            topBar(R.drawable.ic_arrow_back, R.drawable.ic_shop)
        }
    }

    @Composable
    fun topBar(iconStart: Int, iconEnd : Int){
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 42.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Card(shape = RoundedCornerShape(10.dp), backgroundColor = colorResource(id = R.color.darkblue_app), modifier = Modifier.size(37.dp)) {
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
                modifier = Modifier.height(37.dp)
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