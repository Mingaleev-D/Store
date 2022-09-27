package com.example.store.model

import java.io.Serializable

data class TopProductsModel(
  val id: Int,
  val name: String,
  val price: String,
  val image: String,
  var bagCount: Int
) : Serializable
