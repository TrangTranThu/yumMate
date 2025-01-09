package com.example.easyfood.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.easyfood.activities.MealActivity
import com.example.easyfood.apdapters.MostPopularAdapter
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.fragments.HomeFragment.Companion.Meal_ID
import com.example.easyfood.fragments.HomeFragment.Companion.Meal_NAME
import com.example.easyfood.fragments.HomeFragment.Companion.Meal_THUMB
import com.example.easyfood.pojo.CategoryMeals
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomeMeal: Meal
    private lateinit var popularItemsAdapter: MostPopularAdapter

    companion object {
        const val Meal_ID = "com.example.easyfood.fragments.idMeal"
        const val Meal_NAME = "com.example.easyfood.fragments.nameMeal"
        const val Meal_THUMB = "com.example.easyfood.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
        popularItemsAdapter = MostPopularAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerView
        preparePopularItemsRecyclerView()

        homeMvvm.getRandomMeal()
        observerRandomMeal()
        obRandomMealClick()

        homeMvvm.getPopularItems()
        obsersePopularItemsLiveData()
        onPopularItemClick()
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = { meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(Meal_ID, meal.idMeal)
            intent.putExtra(Meal_NAME, meal.strMeal)
            intent.putExtra(Meal_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun obsersePopularItemsLiveData() {
        homeMvvm.obsersePopularItemsLiveData().observe(viewLifecycleOwner,
            { mealList ->
                popularItemsAdapter.setMeals(mealsList = mealList  as ArrayList<CategoryMeals>)
            })
    }

    private fun obRandomMealClick() {
        binding.randomMealCard.setOnClickListener{
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(Meal_ID, randomeMeal.idMeal)
            intent.putExtra(Meal_NAME, randomeMeal.strMeal)
            intent.putExtra(Meal_THUMB, randomeMeal.strMealThumb)
            startActivity(intent)
        }
    }

    // su dụng để quan sát các thay đổi của data từ ViewModel -> cập nhật UI khi data thay đổi.
    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner,
            { meal ->
                Glide.with(this@HomeFragment)
                    .load(meal!!.strMealThumb)
                    .into(binding.imgRandomMeal)
                this.randomeMeal = meal
            })
    }
}

