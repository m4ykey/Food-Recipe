package com.example.meal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meal.data.model.Category
import com.example.meal.databinding.CategoryItemListBinding

class CategoryHomeAdapter : RecyclerView.Adapter<CategoryHomeAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : CategoryItemListBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CategoryItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = differ.currentList[position]
        Glide.with(holder.itemView)
            .load(category.strCategoryThumb)
            .fitCenter()
            .into(holder.binding.imgCategory)
        holder.binding.txtCategory.text = category.strCategory
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}