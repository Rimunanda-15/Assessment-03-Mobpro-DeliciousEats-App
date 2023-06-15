package com.d3if3105.deliciouseats.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.d3if3105.deliciouseats.R
import com.d3if3105.deliciouseats.adapter.CategoryMealsAdapter
import com.d3if3105.deliciouseats.databinding.ActivityCategoriesMealsBinding
import com.d3if3105.deliciouseats.view.HomeFragment
import com.d3if3105.deliciouseats.viewModel.CategoryMealsViewModel

class CategoriesMealsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoriesMealsBinding
    lateinit var categoryMealsViewModel:CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecycleView()

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]

        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        categoryMealsViewModel.observeMealsLiveData().observe(this, Observer { mealList ->
            binding.tvCategoryCount.text = mealList.size.toString()
            categoryMealsAdapter.setMealsList(mealList)
        })
    }

    private fun prepareRecycleView() {
       categoryMealsAdapter = CategoryMealsAdapter()
       binding.rvMeals.apply {
           layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
           adapter = categoryMealsAdapter
       }
    }
}