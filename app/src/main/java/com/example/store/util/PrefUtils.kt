package com.example.store.util

import com.example.store.model.BagModel
import com.example.store.model.TopProductsModel
import com.orhanobut.hawk.Hawk

object PrefUtils {
  const val PREF_FAVORITES = "pref_favorites"
  const val PREF_BAG = "pref_bag"

  fun setFavorite(item: TopProductsModel) {
    val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())

    if (items.firstOrNull { it == item.id } != null) {
      items.remove(item.id)
    } else {
      items.add(item.id)
    }
    Hawk.put(PREF_FAVORITES, items)
  }

  fun getFavoriteList(): ArrayList<Int> {
    return Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
  }

  fun checkFavorite(item: TopProductsModel): Boolean {
    val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
    return items.firstOrNull { it == item.id } != null
  }

  fun setBag(item: TopProductsModel) {
    val items = Hawk.get<ArrayList<BagModel>>(PREF_BAG, arrayListOf<BagModel>())
    val bag = items.firstOrNull { it.product_id == item.id }
    if (bag != null) {
      if (item.bagCount > 0) {
        bag.count = item.bagCount
      } else {
        items.remove(bag)
      }
    }else {
      val newBag = BagModel(item.id,item.bagCount)
      items.add(newBag)
    }
    Hawk.put(PREF_BAG, items)
  }
  fun getBagList():ArrayList<BagModel>{
    return  Hawk.get(PREF_BAG, arrayListOf<BagModel>())
  }
  fun getBagCount(item:TopProductsModel):Int {
    val items = Hawk.get<ArrayList<BagModel>>(PREF_BAG, arrayListOf<BagModel>())
    return items.filter { it.product_id==item.id }.firstOrNull()?.count ?: 0
  }
}