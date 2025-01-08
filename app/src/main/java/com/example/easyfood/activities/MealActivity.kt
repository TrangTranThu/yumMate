package com.example.easyfood.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.fragments.HomeFragment
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealMvvm: MealViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]
        getMealInformationFromIntent()
        setInformationInViews()
        mealMvvm.getMealDetail(mealId)
        observerMealDetailsLiveData()
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observerMealDealsLiveData().observe(this, object : Observer<Meal> {
            override fun onChanged(value: Meal) {
                val meal = value
                binding.tvCategory.text = "Category : ${meal!!.strCategory}"
                binding.tvArea.text = "Are : ${meal.strArea}"
                binding.tvInstructionsSteps.text = meal.strInstructions
            }
        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.Meal_ID)!!
        mealName = intent.getStringExtra(HomeFragment.Meal_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.Meal_THUMB)!!
    }

}