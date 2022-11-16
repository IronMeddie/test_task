package com.ironmeddie.test_task.presentation.ui.cart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentCartBinding
import com.ironmeddie.test_task.presentation.ui.activity.MainActivity
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
                    val cart = cartViewModel.cart.collectAsState()

                    Scaffold(topBar = { CartTopBar(R.drawable.ic_arrow_back, R.drawable.location, findNavController()) }) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                        ) {
                            item {
                                Text(
                                    text = "My Cart",
                                    style = MaterialTheme.typography.h2,
                                    fontSize = 35.sp,
                                    modifier = Modifier.padding(horizontal = 42.dp, vertical = 50.dp)
                                )
                            }

                            item {
                                CartList(cart.value)
                            }
                        }

                    }
                }
            }
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}