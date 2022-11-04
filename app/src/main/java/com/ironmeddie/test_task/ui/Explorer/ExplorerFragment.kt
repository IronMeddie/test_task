package com.ironmeddie.test_task.ui.Explorer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ironmeddie.test_task.R

class ExplorerFragment : Fragment() {

    companion object {
        fun newInstance() = ExplorerFragment()
    }

    private lateinit var viewModel: ExplorerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ExplorerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}