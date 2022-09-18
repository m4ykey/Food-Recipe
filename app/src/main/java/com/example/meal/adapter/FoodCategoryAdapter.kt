package com.example.meal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meal.data.model.Food
import com.example.meal.databinding.CategoryItemListBinding

class FoodCategoryAdapter : RecyclerView.Adapter<FoodCategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : CategoryItemListBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Food>(){
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CategoryItemListBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    val differ = AsyncListDiffer(this, diffUtil)

    private var onItemClick: ((Food) -> Unit)? = null

    fun setOnItemClick(listener: (Food) -> Unit) {
        onItemClick = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val foodCategory = differ.currentList[position]
        Glide.with(holder.itemView)
            .load(foodCategory.strMealThumb)
            .centerCrop()
            .into(holder.binding.imgCategory)
        holder.binding.txtCategory.text = foodCategory.strMeal

        holder.binding.cardview.setOnClickListener {
            onItemClick?.let { it(differ.currentList[position]) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}