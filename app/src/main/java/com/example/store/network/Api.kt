package com.example.store.network

import com.example.store.model.BaseResponse
import com.example.store.model.CategoryModel
import com.example.store.model.OfferModel
import com.example.store.model.TopProductsModel
import com.example.store.model.request.GetProductsByIdRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author : Mingaleev D
 * @data : 18/09/2022
 */

interface Api {
   @GET("get_offers")
   fun getOffers(): Observable<BaseResponse<List<OfferModel>>>

   @GET("get_categories")
   fun getCategories(): Observable<BaseResponse<List<CategoryModel>>>

   @GET("get_top_products")
   fun getTopProducts(): Observable<BaseResponse<List<TopProductsModel>>>

   @GET("get_products/{category_id}")
   fun getCategoriesProducts(
      @Path("category_id") categoryId: Int
   ): Observable<BaseResponse<List<TopProductsModel>>>

   @POST("get_products_by_ids")
   fun getProductsByIds(
      @Body request: GetProductsByIdRequest
   ): Observable<BaseResponse<List<TopProductsModel>>>

}