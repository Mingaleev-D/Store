package com.example.store.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.store.R
import com.example.store.databinding.ActivityMainBinding
import com.example.store.ui.screen.bag.BagFragment
import com.example.store.ui.screen.favorite.FavoriteFragment
import com.example.store.ui.screen.home.HomeFragment
import com.example.store.ui.screen.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding

   private val homeFragment = HomeFragment.newInstance()
   private val favoriteFragment = FavoriteFragment.newInstance()
   private val profileFragment = ProfileFragment.newInstance()
   private val bagFragment = BagFragment.newInstance()
   var activeFragment: Fragment = homeFragment

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      supportFragmentManager.beginTransaction()
         .add(R.id.flContainer, homeFragment, homeFragment.tag).hide(homeFragment).commit()
      supportFragmentManager.beginTransaction()
         .add(R.id.flContainer, favoriteFragment, favoriteFragment.tag).hide(favoriteFragment).commit()
      supportFragmentManager.beginTransaction()
         .add(R.id.flContainer, profileFragment, profileFragment.tag).hide(profileFragment).commit()
      supportFragmentManager.beginTransaction()
         .add(R.id.flContainer, bagFragment, bagFragment.tag).hide(bagFragment).commit()

      supportFragmentManager.beginTransaction().show(activeFragment).commit()

      binding.bottomNavView.setOnNavigationItemSelectedListener {
         if(it.itemId == R.id.actionHome) {
            supportFragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
            activeFragment = homeFragment
         }
         else if(it.itemId == R.id.actionFavorites){
            supportFragmentManager.beginTransaction().hide(activeFragment).show(favoriteFragment).commit()
            activeFragment = favoriteFragment
         }
         else if(it.itemId == R.id.actionCart){
            supportFragmentManager.beginTransaction().hide(activeFragment).show(bagFragment).commit()
            activeFragment = bagFragment
         }
         else if(it.itemId == R.id.actionProfile){
            supportFragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit()
            activeFragment = profileFragment
         }
         return@setOnNavigationItemSelectedListener true
      }

   }
}