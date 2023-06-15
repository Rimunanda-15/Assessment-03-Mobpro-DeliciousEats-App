package com.d3if3105.deliciouseats.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.d3if3105.deliciouseats.activities.MealActivity
import com.d3if3105.deliciouseats.adapter.MostPopularAdapter
import com.d3if3105.deliciouseats.api.RetrofitInstance
import com.d3if3105.deliciouseats.databinding.FragmentHomeBinding
import com.d3if3105.deliciouseats.model.CategoryMeal
import com.d3if3105.deliciouseats.model.Meal
import com.d3if3105.deliciouseats.model.MealList
import com.d3if3105.deliciouseats.viewModel.HomeViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeModel: HomeViewModel
    private lateinit var randomMeal:Meal
    private lateinit var popularItemAdapter:MostPopularAdapter

    companion object{
        const val MEAL_ID = "com.d3if3105.deliciouseats.view.idMeal"
        const val MEAL_NAME = "com.d3if3105.deliciouseats.view.nameMeal"
        const val MEAL_THUMB = "com.d3if3105.deliciouseats.view.thumbMeal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeModel= ViewModelProvider(this)[HomeViewModel::class.java]

        popularItemAdapter = MostPopularAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemRecycleView()

        homeModel.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        homeModel.getPopularItems()
        observePopularItemLiveData()
        onPopularItemClick()
    }

    private fun onPopularItemClick() {
        popularItemAdapter.onitemClick = { meal ->
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)

            startActivity(intent)
        }
    }

    private fun preparePopularItemRecycleView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemAdapter
        }
    }

    private fun observePopularItemLiveData() {
        homeModel.observePopularItemLiveData().observe(viewLifecycleOwner
        ) { mealList ->
            popularItemAdapter.setMeals(mealList = mealList as ArrayList<CategoryMeal>)
        }
    }

    private fun onRandomMealClick() {
        binding.randomMeal.setOnClickListener {
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        homeModel.observeRandomMealLiveData().observe(viewLifecycleOwner,
            { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomMeal)

            this.randomMeal = meal
        })
    }

}