package com.example.store.network

import com.example.store.model.BaseResponse
import com.example.store.model.CategoryModel
import com.example.store.model.OfferModel
import com.example.store.model.TopProductsModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author : Mingaleev D
 * @data : 18/09/2022
 */

interface Api {
   @GET("get_offers")
   fun getOffers():Call<BaseResponse<List<OfferModel>>>

   @GET("get_categories")
   fun getCategories():Call<BaseResponse<List<CategoryModel>>>

   @GET("get_top_products")
   fun getTopProducts():Call<BaseResponse<List<TopProductsModel>>>

}