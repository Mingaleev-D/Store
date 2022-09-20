package com.example.store.ui.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.store.model.CategoryModel
import com.example.store.model.OfferModel
import com.example.store.model.TopProductsModel
import com.example.store.network.repository.ShopRepository

/**
 * @author : Mingaleev D
 * @data : 18/09/2022
 */

class MainViewModel : ViewModel() {

   val repository = ShopRepository()

   val error = MutableLiveData<String>()
   val progress = MutableLiveData<Boolean>()

   val offersData = MutableLiveData<List<OfferModel>>()
   val categoriesData = MutableLiveData<List<CategoryModel>>()
   val topProductsData = MutableLiveData<List<TopProductsModel>>()

   fun getOffers(){
      repository.getOffers(error,progress, offersData)
   }
   fun getCategories(){
      repository.getCategories(error,categoriesData)
   }
   fun getTopProducts(){
      repository.getTopProducts(error,topProductsData)
   }
   fun getByCategoriesProducts(id:Int){
      repository.getByCategoriesProducts(id,error,topProductsData)
   }
   fun getProductsByIds(ids:List<Int>){
      repository.getProductsByIds(ids,error,progress, topProductsData)
   }

}
