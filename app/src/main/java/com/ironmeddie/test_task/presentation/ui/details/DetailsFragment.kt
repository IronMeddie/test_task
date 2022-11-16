package com.ironmeddie.test_task.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentDetailsBinding
import com.ironmeddie.test_task.presentation.ui.activity.MainActivity
import com.ironmeddie.test_task.presentation.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        (activity as MainActivity).hideBottomMenu()

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MyTheme {
                    val details = viewmodel.details.collectAsState()
                    Scaffold(topBar = {
                        DetailsTopBar(
                            R.drawable.ic_arrow_back,
                            R.drawable.ic_shop,
                            findNavController()
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
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}