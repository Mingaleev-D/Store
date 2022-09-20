package com.example.store.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.store.R
import com.example.store.databinding.FragmentHomeBinding
import com.example.store.model.BaseResponse
import com.example.store.model.CategoryModel
import com.example.store.model.OfferModel
import com.example.store.network.Api
import com.example.store.network.RetrofitInstance
import com.example.store.ui.adapter.CategoryAdapter
import com.example.store.ui.adapter.CategoryAdapterCallback
import com.example.store.ui.adapter.ProductAdapter
import com.example.store.ui.screen.MainViewModel
import com.example.store.util.Constants.BASE_URL
import com.example.store.util.Constants.IMAGE_BASE
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
  private var mBinding: FragmentHomeBinding? = null
  private val binding get() = mBinding!!

  private var imageList = ArrayList<SlideModel>()

  lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProvider(this)[MainViewModel::class.java]
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    mBinding = FragmentHomeBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.swiper.setOnRefreshListener {
      loadData()
    }

    binding.rvCategories.layoutManager =
      LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

    viewModel.error.observe(requireActivity(), Observer {
      Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
    })

    viewModel.progress.observe(requireActivity(), Observer {
      binding.swiper.isRefreshing = it
    })

    viewModel.offersData.observe(requireActivity(), Observer {
      imageList.add(
        SlideModel(
          IMAGE_BASE + it[0].image,
          ScaleTypes.CENTER_CROP
        )
      )
      imageList.add(
        SlideModel(
          IMAGE_BASE + it[1].image,
          ScaleTypes.CENTER_CROP
        )
      )
      imageList.add(
        SlideModel(
          IMAGE_BASE + it[2].image,
          ScaleTypes.CENTER_CROP
        )
      )
      binding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    })
    viewModel.categoriesData.observe(requireActivity(), Observer {
      binding.rvCategories.adapter =
        CategoryAdapter(it, object : CategoryAdapterCallback {
          override fun onClickItem(item: CategoryModel) {
            viewModel.getByCategoriesProducts(item.id)
          }
        })
    })

    viewModel.topProductsData.observe(requireActivity(), Observer {
      binding.rvProducts.adapter = ProductAdapter(it)
    })

    loadData()

  }

  fun loadData() {
    viewModel.getOffers()
    viewModel.getCategories()
    viewModel.getTopProducts()
  }

  override fun onDestroy() {
    super.onDestroy()
    mBinding = null
  }

  companion object {
    @JvmStatic
    fun newInstance() = HomeFragment()
  }


}