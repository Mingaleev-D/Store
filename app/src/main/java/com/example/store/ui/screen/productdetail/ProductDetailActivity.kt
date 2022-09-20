package com.example.store.ui.screen.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.store.R
import com.example.store.databinding.ActivityProductDetailBinding
import com.example.store.model.TopProductsModel
import com.example.store.util.Constants.EXTRA_DATA
import com.example.store.util.Constants.IMAGE_BASE
import com.example.store.util.PrefUtils

class ProductDetailActivity : AppCompatActivity() {
   private lateinit var binding:ActivityProductDetailBinding
   lateinit var item:TopProductsModel

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityProductDetailBinding.inflate(layoutInflater)
      setContentView(binding.root)

      binding.cvDetailsArrow.setOnClickListener {
         finish()
      }
      binding.cvDetailsFavorites.setOnClickListener {
         PrefUtils.setFavorite(item)

         if(PrefUtils.checkFavorite(item)) {
            binding.imgDetFavorites.setImageResource(R.drawable.ic_love)
         }else {
            binding.imgDetFavorites.setImageResource(R.drawable.ic_heart_favotite)
         }
      }

      item = intent.getSerializableExtra(EXTRA_DATA) as TopProductsModel

      binding.apply {
         tvDetailsTitle.text = item.name
         tvProductsName.text = item.name
         tvPriceProduct.text = item.price

         if(PrefUtils.checkFavorite(item)) {
            binding.imgDetFavorites.setImageResource(R.drawable.ic_love)
         }else {
            binding.imgDetFavorites.setImageResource(R.drawable.ic_heart_favotite)
         }

         Glide.with(this@ProductDetailActivity).load(IMAGE_BASE+item.image).into(imgDetProducts)
      }

   }
}