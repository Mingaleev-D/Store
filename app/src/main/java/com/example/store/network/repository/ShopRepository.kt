package com.example.store.network.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.store.model.BaseResponse
import com.example.store.model.CategoryModel
import com.example.store.model.OfferModel
import com.example.store.model.TopProductsModel
import com.example.store.model.request.GetProductsByIdRequest
import com.example.store.network.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 18/09/2022
 */

class ShopRepository {
  val compositeDisposable = CompositeDisposable()

  //-------------------OfferModel response -------------------------------------------
  fun getOffers(
    error: MutableLiveData<String>,
    progress: MutableLiveData<Boolean>,
    success: MutableLiveData<List<OfferModel>>
  ) {
    progress.value = true
    compositeDisposable.add(
      RetrofitInstance.storeApi.getOffers()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<BaseResponse<List<OfferModel>>>() {
          override fun onNext(t: BaseResponse<List<OfferModel>>) {
            progress.value = false
            if (t.success) {
              success.value = t.data
            } else {
              error.value = t.message
            }
          }

          override fun onError(e: Throwable) {
            progress.value = false
            error.value = e.localizedMessage
          }

          override fun onComplete() {
            //TODO("Not yet implemented")
          }
        })
    )
  }
  //-------------------OfferModel response -------------------------------------------

  // -------------------category response -------------------------------------------
  fun getCategories(
    error: MutableLiveData<String>,
    success: MutableLiveData<List<CategoryModel>>
  ) {
    compositeDisposable.add(
      RetrofitInstance.storeApi.getCategories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<BaseResponse<List<CategoryModel>>>() {
          override fun onNext(t: BaseResponse<List<CategoryModel>>) {

            if (t.success) {
              success.value = t.data
            } else {
              error.value = t.message
            }
          }

          override fun onError(e: Throwable) {

            error.value = e.localizedMessage
          }

          override fun onComplete() {
            //TODO("Not yet implemented")
          }
        })
    )

  }

  // -------------------category response -------------------------------------------
// -------------------top_products response -------------------------------------------
  fun getTopProducts(
    error: MutableLiveData<String>,
    success: MutableLiveData<List<TopProductsModel>>
  ) {
    compositeDisposable.add(
      RetrofitInstance.storeApi.getTopProducts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<BaseResponse<List<TopProductsModel>>>() {
          override fun onNext(t: BaseResponse<List<TopProductsModel>>) {

            if (t.success) {
              success.value = t.data
            } else {
              error.value = t.message
            }
          }

          override fun onError(e: Throwable) {

            error.value = e.localizedMessage
          }

          override fun onComplete() {
            //TODO("Not yet implemented")
          }
        })
    )
  }
  // -------------------top_products response -------------------------------------------

  // -------------------category_top_products response -------------------------------------------
  fun getByCategoriesProducts(
    id: Int,
    error: MutableLiveData<String>,
    success: MutableLiveData<List<TopProductsModel>>
  ) {
    compositeDisposable.add(
      RetrofitInstance.storeApi.getCategoriesProducts(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<BaseResponse<List<TopProductsModel>>>() {
          override fun onNext(t: BaseResponse<List<TopProductsModel>>) {

            if (t.success) {
              success.value = t.data
            } else {
              error.value = t.message
            }
          }

          override fun onError(e: Throwable) {

            error.value = e.localizedMessage
          }

          override fun onComplete() {
            //TODO("Not yet implemented")
          }
        })
    )
  }

  // -------------------category_top_products response -------------------------------------------
// -------------------products request(POST) -------------------------------------------
  fun getProductsByIds(
    ids: List<Int>,
    error: MutableLiveData<String>,
    progress: MutableLiveData<Boolean>,
    success: MutableLiveData<List<TopProductsModel>>
  ) {
    progress.value = true

    compositeDisposable.add(
      RetrofitInstance.storeApi.getProductsByIds(GetProductsByIdRequest(ids))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<BaseResponse<List<TopProductsModel>>>() {
          override fun onNext(t: BaseResponse<List<TopProductsModel>>) {
            progress.value = false
            if (t.success) {
              success.value = t.data
            } else {
              error.value = t.message
            }
          }

          override fun onError(e: Throwable) {
            progress.value = false
            error.value = e.localizedMessage
          }

          override fun onComplete() {
            //TODO("Not yet implemented")
          }
        })
    )
  }
// -------------------products request(POST) -------------------------------------------
}