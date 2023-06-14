package com.d3if3105.deliciouseats.api

import com.d3if3105.deliciouseats.model.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {
    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}