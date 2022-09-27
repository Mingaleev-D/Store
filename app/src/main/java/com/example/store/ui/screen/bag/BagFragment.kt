package com.example.store.ui.screen.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.store.databinding.FragmentBagBinding
import com.example.store.ui.adapter.BagAdapter
import com.example.store.ui.screen.MainViewModel
import com.example.store.util.PrefUtils
import kotlinx.android.synthetic.main.fragment_bag.*
import kotlinx.android.synthetic.main.fragment_favorite.*


class BagFragment : Fragment() {

   private var mBinding: FragmentBagBinding? = null
   private val binding get() = mBinding!!

   lateinit var viewModel:MainViewModel

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[MainViewModel::class.java]


   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      mBinding = FragmentBagBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      viewModel.progress.observe(viewLifecycleOwner) {
         swipe.isRefreshing = it
      }
      viewModel.error.observe(viewLifecycleOwner) {
         Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
      }
      viewModel.topProductsData.observe(viewLifecycleOwner) {
         binding.rvBag.adapter = BagAdapter(it)
      }
      binding.swipeBag.setOnRefreshListener {
         loadData()
      }
      //loadData()
   }
   fun loadData(){
      viewModel.getProductsByIds(PrefUtils.getBagList().map { it.product_id })
   }

   override fun onDestroy() {
      super.onDestroy()
      mBinding = null
   }

   companion object {

      @JvmStatic
      fun newInstance() = BagFragment()
   }
}