package com.example.meal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meal.data.model.Food
import com.example.meal.databinding.SearchItemListBinding

class SearchFavAdapter : RecyclerView.Adapter<SearchFavAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : SearchItemListBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Food>(){
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SearchItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(differ.currentList[position].strMealThumb)
            .centerCrop()
            .into(holder.binding.imgFoodSearch)
        holder.binding.txtFood.text = differ.currentList[position].strMeal
        holder.binding.txtDescription.text = differ.currentList[position].strInstructions
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}