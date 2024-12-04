package com.example.easyfood.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {

    // Base URL của Api
//    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    // Tạo một object Retrofit duy nhất (singleton instance)
    val api: MealApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create()) // Định nghĩa converter (JSON -> object)
            .build()
            .create(MealApi::class.java) // Tạo instance của MealApi
    }

    /*Phương thức này trả về một instance của ApiService
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java) // Tạo API interface để gọi các endpoint
    }*/
}