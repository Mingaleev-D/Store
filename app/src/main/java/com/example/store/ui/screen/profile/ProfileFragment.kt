package com.example.store.ui.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.store.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

   private var mBinding: FragmentProfileBinding? = null
   private val binding get() = mBinding!!

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      mBinding = FragmentProfileBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onDestroy() {
      super.onDestroy()
      mBinding = null
   }

   companion object {

      @JvmStatic
      fun newInstance() = ProfileFragment()
   }
}