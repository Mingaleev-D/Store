package com.example.store.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.CategoryItemBinding
import com.example.store.model.CategoryModel
import kotlinx.android.synthetic.main.category_item.view.*

/**
 * @author : Mingaleev D
 * @data : 18/09/2022
 */

class CategoryAdapter(val items: List<CategoryModel>, val callback: CategoryAdapterCallback) :
  RecyclerView.Adapter<CategoryAdapter.CategoryMyViewHolder>() {

  class CategoryMyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMyViewHolder {
    return CategoryMyViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
    )
  }

  override fun onBindViewHolder(holder: CategoryMyViewHolder, position: Int) {
    val item = items[position]
    holder.itemView.tv_title_ci.text = item.title
    holder.itemView.setOnClickListener {
      items.forEach {
        it.checked = false
      }
      item.checked = true
      callback.onClickItem(item)
      notifyDataSetChanged()
    }

    if (item.checked) {
      holder.itemView.cv_category_item.setCardBackgroundColor(
        ContextCompat.getColor(
          holder.itemView.context,
          R.color.purple_500
        )
      )
      holder.itemView.tv_title_ci.setTextColor(Color.WHITE)
    } else {
      holder.itemView.cv_category_item.setCardBackgroundColor(
        ContextCompat.getColor(
          holder.itemView.context,
          R.color.purple_100
        )
      )
      holder.itemView.tv_title_ci.setTextColor(Color.BLACK)

    }
  }

  override fun getItemCount(): Int = items.size

}

interface CategoryAdapterCallback {
  fun onClickItem(item: CategoryModel)
}