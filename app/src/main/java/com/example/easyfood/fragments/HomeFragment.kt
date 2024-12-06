package com.example.easyfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel

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
    }
    //used to observer các thay đổi của data từ ViewModel -> cập nhật UI khi data thay đổi.
    private fun observerRandomMeal() {
        // homeMvvm: là instance của ViewModel
        // viewLifecycleOwner: Là object đại diện cho vòng đời of Fragment
        // object : Observer<Meal>: Đây là 1 object Observer được định nghĩa để lắng nghe các thay đổi của LiveData<Meal>.
        // Khi có thay đổi trong LiveData, phương thức onChanged() sẽ được gọi.
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner, object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                Glide.with(this@HomeFragment)
                    .load(value.strMealThumb)
                    .into(binding.imgRandomMeal)
            }
        })

    }
}

