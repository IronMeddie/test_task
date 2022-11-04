package com.ironmeddie.test_task.ui.Explorer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironmeddie.test_task.R
import com.ironmeddie.test_task.databinding.FragmentExplorerBinding
import com.ironmeddie.test_task.domain.models.CategoryItems
import com.ironmeddie.test_task.ui.adapters.RcMainAdapter

class ExplorerFragment : Fragment() {

    private val adapter = RcMainAdapter()
    private lateinit var binding: FragmentExplorerBinding

    companion object {
        fun newInstance() = ExplorerFragment()
    }

    private lateinit var viewModel: ExplorerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentExplorerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainScreenRc.adapter = adapter
        binding.mainScreenRc.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)


        val listCategories =  listOf(
            CategoryItems("Phones", R.drawable.ic_phones),
            CategoryItems("Computers", R.drawable.ic_phones),
            CategoryItems("Kotiki", R.drawable.ic_phones),
            CategoryItems("sobachki", R.drawable.ic_phones),
            CategoryItems("Botinki", R.drawable.ic_phones),)
        adapter.updateList(listCategories)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ExplorerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}