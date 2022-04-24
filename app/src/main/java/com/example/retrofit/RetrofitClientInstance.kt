package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {
    companion object{
        //private lateinit var retrofit: Retrofit
        private val BASE_URL="https://www.themealdb.com/api/json/v1/1/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
      /*  val retrofitInstance :Retrofit
            get() {
                if (retrofit==null){
                    retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }*/
    }

}