package com.example.store.network

import com.example.store.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

   val storeApi: Api by lazy {
      Retrofit.Builder()
         .addConverterFactory(GsonConverterFactory.create())
         .baseUrl(Constants.BASE_URL)
         .build()
         .create(Api::class.java)
   }
}
