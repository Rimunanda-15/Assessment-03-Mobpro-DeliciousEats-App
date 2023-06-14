package com.d3if3105.deliciouseats.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.d3if3105.deliciouseats.api.RetrofitInstance
import com.d3if3105.deliciouseats.databinding.FragmentHomeBinding
import com.d3if3105.deliciouseats.model.Meal
import com.d3if3105.deliciouseats.model.MealList
import com.d3if3105.deliciouseats.viewModel.HomeViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeModel= ViewModelProvider(this)[HomeViewModel::class.java]
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

        homeModel.getRandomMeal()
        observerRandomMeal()

    }

    private fun observerRandomMeal() {
        homeModel.observeRandomMealLiveData().observe(viewLifecycleOwner,object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                Glide.with(this@HomeFragment)
                    .load(t!!.strMealThumb).into(binding.imgRandomMeal)
            }

        })
    }

}