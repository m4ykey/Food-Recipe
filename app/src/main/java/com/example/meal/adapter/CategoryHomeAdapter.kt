package com.example.meal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meal.data.model.FoodCategory
import com.example.meal.databinding.CategoryItemListBinding

class CategoryHomeAdapter : RecyclerView.Adapter<CategoryHomeAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : CategoryItemListBinding) : RecyclerView.ViewHolder(binding.root)

    private var categoryList = ArrayList<FoodCategory>()
    fun setCategories(categories : List<FoodCategory>){
        this.categoryList = categories as ArrayList<FoodCategory>
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<FoodCategory>(){
        override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CategoryItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoryList[position].strCategoryThumb)
            .fitCenter()
            .into(holder.binding.imgCategory)
        holder.binding.txtCategory.text = categoryList[position].strCategory
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}