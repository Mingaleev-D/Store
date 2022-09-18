package com.example.store.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.store.model.BaseResponse
import com.example.store.model.CategoryModel
import com.example.store.model.OfferModel
import com.example.store.network.RetrofitInstance
import com.example.store.ui.adapter.CategoryAdapter
import com.example.store.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 18/09/2022
 */

class MainViewModel : ViewModel() {

   val error = MutableLiveData<String>()

   val offersData = MutableLiveData<List<OfferModel>>()
   val categoriesData = MutableLiveData<List<CategoryModel>>()

   //-------------------OfferModel response -------------------------------------------
   fun getOffers() {
      RetrofitInstance.storeApi.getOffers().enqueue(object :
         Callback<BaseResponse<List<OfferModel>>> {
         @SuppressLint("NullSafeMutableLiveData")
         override fun onResponse(
            call: Call<BaseResponse<List<OfferModel>>>,
            response: Response<BaseResponse<List<OfferModel>>>
         ) {
            if (response.isSuccessful && response.body()!!.success) {
                  offersData.value = response.body()!!.data
            } else {
               error.value = response.body()?.message ?: response.message()
            }
         }
         override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
            error.value = t.localizedMessage
         }
      })
   }
   //-------------------OfferModel response -------------------------------------------

   // -------------------category response -------------------------------------------
   fun getCategories(){
      RetrofitInstance.storeApi.getCategories()
         .enqueue(object : Callback<BaseResponse<List<CategoryModel>>> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
               call: Call<BaseResponse<List<CategoryModel>>>,
               response: Response<BaseResponse<List<CategoryModel>>>
            ) {
               if (response.isSuccessful && response.body()?.success!!) {
                  categoriesData.value = response.body()!!.data
               }else {
                  error.value = response.body()?.message ?: response.message()
               }
            }

            override fun onFailure(call: Call<BaseResponse<List<CategoryModel>>>, t: Throwable) {
               error.value = t.localizedMessage
            }

         })
   }
   // -------------------category response -------------------------------------------
}
