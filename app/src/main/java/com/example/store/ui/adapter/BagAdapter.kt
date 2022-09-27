package com.example.store.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.store.R
import com.example.store.model.TopProductsModel
import com.example.store.util.Constants.IMAGE_BASE
import kotlinx.android.synthetic.main.bag_item_layout.view.*

/**
 * @author : Mingaleev D
 * @data : 27/09/2022
 */

class BagAdapter(val items: List<TopProductsModel>) :
  RecyclerView.Adapter<BagAdapter.MyViewHolder>() {
  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    return MyViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.bag_item_layout, parent, false)
    )
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val item = items[position]

    holder.itemView.tv_price.text = item.price
    holder.itemView.tv_products_name.text = item.name

    Glide.with(holder.itemView).load(IMAGE_BASE+item.image).into(holder.itemView.imgProduct)

    holder.itemView.tv_count.text = item.bagCount.toString()
  }

  override fun getItemCount(): Int = items.count()
}