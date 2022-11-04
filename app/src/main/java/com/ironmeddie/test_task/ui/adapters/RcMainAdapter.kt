package com.ironmeddie.test_task.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ironmeddie.test_task.databinding.CategoryItemRecyclerBinding
import com.ironmeddie.test_task.domain.models.CategoryItems

class RcMainAdapter: RecyclerView.Adapter<RcMainAdapter.ItemViewHolder>() {



    class ItemViewHolder(binding: CategoryItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val mb = binding
        fun bind(category: CategoryItems) {
            mb.apply {
                categoryIcon.setImageResource(category.icon)
                categoryNameTv.text = category.name
            }
        }
        fun choosenone(){
//        mb.categoryBackgroundCircle.setBackgroundColor(R.color.orange_app)
//        mb.categoryNameTv.setTextColor(R.color.orange_app)
        }
    }


    private val callback = object : DiffUtil.ItemCallback<CategoryItems>() {
        override fun areItemsTheSame(oldItem: CategoryItems, newItem: CategoryItems): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CategoryItems, newItem: CategoryItems): Boolean {
            return oldItem == newItem
        }

//        override fun getChangePayload(oldItem: CategoryItems, newItem: CategoryItems): Any? {
//            if (oldItem.choise != newItem.choise) return newItem.choise
//            return super.getChangePayload(oldItem, newItem)
//        }

    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ItemViewHolder {
        val b = CategoryItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(b)
    }



        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val category = differ.currentList[position]
            holder.bind(category)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(category)
                }
            }
        }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener: ((CategoryItems) -> Unit)? = null

    fun setonItemClikListener(listener: (CategoryItems) -> Unit) {
        onItemClickListener = listener
    }


    fun updateList(list: List<CategoryItems>) {
        differ.submitList(list)
    }
}








