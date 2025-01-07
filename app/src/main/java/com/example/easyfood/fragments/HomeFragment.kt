package com.example.easyfood.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.activities.MealActivity
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMeal: Meal

    companion object {
        const val Meal_ID = "com.example.easyfood.fragments.idMeal"
        const val Meal_NAME = "com.example.easyfood.fragments.nameMeal"
        const val Meal_THUMB = "com.example.easyfood.fragments.thumbMeal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
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

        homeMvvm.getRandomMeal()
        observerRandomMeal()

        obRandomMealClick()
    }

    private fun obRandomMealClick() {
        binding.randomMealCard.setOnClickListener{
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(Meal_ID, randomMeal.idMeal)
            intent.putExtra(Meal_NAME, randomMeal.strMeal)
            intent.putExtra(Meal_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    // su dụng để quan sát các thay đổi của data từ ViewModel -> cập nhật UI khi data thay đổi.
    private fun observerRandomMeal() {
        // homeMvvm: là instance của ViewModel
        // viewLifecycleOwner: Là object đại diện cho vòng đời of Fragment
        // object : Observer<Meal>: Đây là 1 object Observer được định nghĩa để lắng nghe các thay đổi của LiveData<Meal>.
        // Khi có thay đổi trong LiveData, phương thức onChanged() sẽ được gọi.
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner,
            { meal ->
                Glide.with(this@HomeFragment)
                    .load(meal!!.strMealThumb)
                    .into(binding.imgRandomMeal)
                this.randomMeal = meal
            })
    }
}

