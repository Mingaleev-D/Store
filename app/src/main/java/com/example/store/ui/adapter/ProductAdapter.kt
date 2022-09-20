package com.example.store.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.store.R
import com.example.store.model.TopProductsModel
import com.example.store.ui.screen.productdetail.ProductDetailActivity
import com.example.store.util.Constants.EXTRA_DATA
import com.example.store.util.Constants.IMAGE_BASE
import kotlinx.android.synthetic.main.top_product_item.view.*

/**
 * @author : Mingaleev D
 * @data : 18/09/2022
 */

class ProductAdapter(val items: List<TopProductsModel>) :
   RecyclerView.Adapter<ProductAdapter.MyProductViewHolder>() {

   class MyProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProductViewHolder {
      return MyProductViewHolder(
         LayoutInflater.from(parent.context).inflate(R.layout.top_product_item, parent, false)
      )
   }

   override fun onBindViewHolder(holder: MyProductViewHolder, position: Int) {
      val item = items[position]

      Glide.with(holder.itemView.context).load(IMAGE_BASE + item.image)
         .into(holder.itemView.findViewById(R.id.imgProduct))
      holder.itemView.tv_products_name.text = item.name
      holder.itemView.tv_price.text = item.price

      holder.itemView.setOnClickListener {
         val intent = Intent(it.context,ProductDetailActivity::class.java)
         intent.putExtra(EXTRA_DATA,item)
         it.context.startActivity(intent)


         }
      }

   override fun getItemCount(): Int = items.size
}