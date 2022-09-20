package com.example.store.network

import com.example.store.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

  val storeApi: Api by lazy {
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(Constants.BASE_URL)
      .build()
      .create(Api::class.java)
  }
}
