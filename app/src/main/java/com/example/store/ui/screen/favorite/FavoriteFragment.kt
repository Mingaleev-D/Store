package com.example.store.ui.screen.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.store.databinding.FragmentFavoriteBinding
import com.example.store.ui.adapter.ProductAdapter
import com.example.store.ui.screen.MainViewModel
import com.example.store.util.PrefUtils
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment() {

   private var mBinding: FragmentFavoriteBinding? = null
   private val binding get() = mBinding!!

   lateinit var viewModel: MainViewModel

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[MainViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      mBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      viewModel.topProductsData.observe(requireActivity()) {
         binding.rvFavProduct.adapter = ProductAdapter(it)
      }
      viewModel.progress.observe(requireActivity()) {
         binding.swipe.isRefreshing = it
      }
      swipe.setOnRefreshListener {
         loadData()
      }
      loadData()

   }

   fun loadData() {
      viewModel.getProductsByIds(PrefUtils.getFavoriteList())

   }

   override fun onDestroy() {
      super.onDestroy()
      mBinding = null
   }

   companion object {

      @JvmStatic
      fun newInstance() = FavoriteFragment()
   }
}