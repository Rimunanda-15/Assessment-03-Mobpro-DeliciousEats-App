package com.d3if3105.deliciouseats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.d3if3105.deliciouseats.databinding.PopularItemsBinding
import com.d3if3105.deliciouseats.model.CategoryMeal
import com.d3if3105.deliciouseats.model.MealList

class MostPopularAdapter():RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    lateinit var onitemClick:((CategoryMeal) -> Unit)
    private var mealList = ArrayList<CategoryMeal>()

    fun setMeals(mealList: ArrayList<CategoryMeal>){
        this.mealList = mealList
        notifyDataSetChanged()
    }

    class PopularMealViewHolder( val binding:PopularItemsBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return mealList.size

    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
       Glide.with(holder.itemView)
           .load(mealList[position].strMealThumb)
           .into(holder.binding.imgPopularMealItem)

       holder.itemView.setOnClickListener {
           onitemClick.invoke(mealList[position])
       }
    }

}