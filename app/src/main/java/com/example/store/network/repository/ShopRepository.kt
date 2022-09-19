package com.example.store.network.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.store.model.BaseResponse
import com.example.store.model.CategoryModel
import com.example.store.model.OfferModel
import com.example.store.model.TopProductsModel
import com.example.store.network.RetrofitInstance
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 18/09/2022
 */

class ShopRepository {

   //-------------------OfferModel response -------------------------------------------
   fun getOffers(error:MutableLiveData<String>,progress:MutableLiveData<Boolean>, success:MutableLiveData<List<OfferModel>>) {
      progress.value = true
      RetrofitInstance.storeApi.getOffers().enqueue(object :
         Callback<BaseResponse<List<OfferModel>>> {
         @SuppressLint("NullSafeMutableLiveData")
         override fun onResponse(
            call: Call<BaseResponse<List<OfferModel>>>,
            response: Response<BaseResponse<List<OfferModel>>>
         ) {
            progress.value = false
            if (response.isSuccessful && response.body()!!.success) {
               success.value = response.body()!!.data
            } else {
               error.value = response.body()?.message ?: response.message()
            }
         }

         override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
            error.value = t.localizedMessage
            progress.value = false
         }
      })
   }
   //-------------------OfferModel response -------------------------------------------

   // -------------------category response -------------------------------------------
   fun getCategories(error:MutableLiveData<String>, success:MutableLiveData<List<CategoryModel>>) {
      RetrofitInstance.storeApi.getCategories()
         .enqueue(object : Callback<BaseResponse<List<CategoryModel>>> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
               call: Call<BaseResponse<List<CategoryModel>>>,
               response: Response<BaseResponse<List<CategoryModel>>>
            ) {
               if (response.isSuccessful && response.body()?.success!!) {
                  success.value = response.body()!!.data
               } else {
                  error.value = response.body()?.message ?: response.message()
               }
            }

            override fun onFailure(call: Call<BaseResponse<List<CategoryModel>>>, t: Throwable) {
               error.value = t.localizedMessage
            }

         })
   }

   // -------------------category response -------------------------------------------
// -------------------top_products response -------------------------------------------
   fun getTopProducts(error:MutableLiveData<String>, success:MutableLiveData<List<TopProductsModel>>) {
      RetrofitInstance.storeApi.getTopProducts()
         .enqueue(object : Callback<BaseResponse<List<TopProductsModel>>> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
               call: Call<BaseResponse<List<TopProductsModel>>>,
               response: Response<BaseResponse<List<TopProductsModel>>>
            ) {
               if(response.isSuccessful && response.body()!!.success){
                  success.value = response.body()!!.data
               }else{
                  error.value = response.body()?.message ?: response.message()
               }
            }

            override fun onFailure(call: Call<BaseResponse<List<TopProductsModel>>>, t: Throwable) {
               error.value = t.localizedMessage
            }
         })
   }
   // -------------------top_products response -------------------------------------------
}