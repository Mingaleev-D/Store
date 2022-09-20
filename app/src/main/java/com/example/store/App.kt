package com.example.store

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.orhanobut.hawk.Hawk

/**
 * @author : Mingaleev D
 * @data : 19/09/2022
 */

class App:MultiDexApplication(){
   override fun onCreate() {
      super.onCreate()
      MultiDex.install(this)
      Hawk.init(this).build()
   }
}