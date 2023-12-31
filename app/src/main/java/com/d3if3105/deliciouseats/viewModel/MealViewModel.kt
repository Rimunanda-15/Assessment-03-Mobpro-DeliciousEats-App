package com.d3if3105.deliciouseats.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d3if3105.deliciouseats.activities.MealActivity
import com.d3if3105.deliciouseats.api.RetrofitInstance
import com.d3if3105.deliciouseats.model.Meal
import com.d3if3105.deliciouseats.model.MealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel:ViewModel() {
    private val mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id:String){
        RetrofitInstance.foodApi.getMealDetails(id).enqueue(object : Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body()!= null){
                    mealDetailsLiveData.value=response.body()!!.meals[0]
                }
                else
                    return

            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealActivity",t.message.toString())
            }
        })
    }

    fun observeMealDetailLiveData():LiveData<Meal>{
        return  mealDetailsLiveData
    }

}