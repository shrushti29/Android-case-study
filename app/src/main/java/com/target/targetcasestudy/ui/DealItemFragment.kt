package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.local.DealItem
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.ui.state.DealControlState
import com.target.targetcasestudy.ui.viewModel.DealProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class DealItemFragment : Fragment() {
    private lateinit var binding: FragmentDealItemBinding
    private val viewModel: DealProductViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealItemBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showLoading()
        super.onViewCreated(view, savedInstanceState)
        observeArguments()
        observeSharedFlow()
    }

    private fun observeSharedFlow() {
        viewModel.getDealControlState().onEach {
            when (it) {
                is DealControlState.FetchItemById -> {
                    hideLoading()
                    it.item.run {
                        Glide.with(binding.dealProductImage.context).load(imageUrl)
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                            .into(binding.dealProductImage)

                        binding.dealProductTitle.text = title
                        binding.addToCartBtn.setOnClickListener{
                            showToast("Hurray!! item added to cart")
                        }
                        binding.productDetailDesc.text = description
                        if (salePrice != null) {
                            binding.dealProductSalePrice.text = salePrice.displayString
                            "reg ${regularPrice.displayString}".also {
                                binding.dealProductRegPrice.text = it
                            }
                        } else {
                            binding.dealProductSalePrice.text = regularPrice.displayString
                        }
                        binding.dealProductSellMode.text = fulfillment
                        binding.productDetailTitle.text = "Product Details"

                    }
                }

                is DealControlState.Loading -> {
                    showLoading()

                }

                else -> Unit
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading() {
        binding.scrollView.visibility = View.GONE
        binding.loadingSpinner.visibility = View.VISIBLE

    }

    private fun hideLoading() {
        binding.loadingSpinner.visibility = View.GONE
        binding.scrollView.visibility = View.VISIBLE
    }

    private fun observeArguments() {
        val id = arguments?.getInt("id")
        id?.let {
            viewModel.fetchItembyId(id)
        }
    }


}

