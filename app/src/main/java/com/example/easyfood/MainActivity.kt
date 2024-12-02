package com.example.easyfood

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.easyfood.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo binding trong onCreate
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Thiết lập layout của Activity
        setContentView(binding.root)

        // viewPager

        /* action move fragment */
        val bottomNavigation = binding.btmNav
        val navController = Navigation.findNavController(this, R.id.host_fragment)

        NavigationUI.setupWithNavController(bottomNavigation,navController)

     /*   val bottomNavigation = binding.btmNav
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            //item.itemId: item là đối tượng của MenuItem mà người dùng đã chọn.
            // Thuộc tính itemId chứa ID của item đó (ví dụ R.id.homeFragment).
            // Mỗi item trong menu đều có một itemId riêng biệt, và đây là giá trị mà when sử dụng để so sánh.
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_favorites -> {
                    Toast.makeText(this, "favorite", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_categories -> {
                    Toast.makeText(this, "Categoties", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
*/

    }
}