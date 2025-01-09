package com.example.easyfood.apdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.easyfood.pojo.CategoryMeals

class MostPopularAdapter() : RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHodel>() {

    lateinit var onItemClick:((CategoryMeals) -> Unit)
    // mealsList là một danh sách các đối tượng CategoryMeals,
    // mỗi đối tượng đại diện cho một món ăn cụ thể
    private var mealsList = ArrayList<CategoryMeals>()

    //cập nhật danh sách món ăn
    fun setMeals(mealsList: ArrayList<CategoryMeals>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    // Đây là lớp ViewHolder giữ dữ liệu về giao diện của từng phần tử trong RecyclerView. Ở đây,
    // PopulaItemsBinding được sử dụng để liên kết với layout của từng mục (item) trong danh sách
    // => truyen view moi item vao ds
    class PopularMealViewHodel(val binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Layout của từng item sẽ được "inflate" (nạp) từ XML thông qua PopulaItemsBinding.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHodel {
        return PopularMealViewHodel(
            PopularItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    //gán dữ liệu từ danh sách mealsList vào ViewHolder tại vị trí chỉ định (vị trí position)
    override fun onBindViewHolder(holder: PopularMealViewHodel, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)
        holder.itemView.setOnClickListener{
            onItemClick.invoke(mealsList[position])
        }

    }
}