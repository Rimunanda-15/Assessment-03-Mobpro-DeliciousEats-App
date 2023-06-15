package com.d3if3105.deliciouseats.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.d3if3105.deliciouseats.model.Meal

@Dao
interface MealDao {
    @Insert
     fun upsert(meal: Meal)

    @Delete
     fun delete(meal: Meal)

    @Query("SELECT * FROM mealInfromation")
    fun getAllMeals():LiveData<List<Meal>>


}