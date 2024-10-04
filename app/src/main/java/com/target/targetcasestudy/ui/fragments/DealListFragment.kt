package com.target.targetcasestudy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.ui.adapter.DealItemAdapter
import com.target.targetcasestudy.ui.state.DealControlState
import com.target.targetcasestudy.ui.state.OnDealClickListener
import com.target.targetcasestudy.ui.viewModel.DealProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealListFragment : Fragment(), OnDealClickListener {
    private lateinit var binding: FragmentDealListBinding
    private val viewModel: DealProductViewModel by activityViewModels()
    private lateinit var adapter: DealItemAdapter
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealListBinding.inflate(inflater, container, false)
        navController = findNavController()
        adapter = DealItemAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelState()
    }

    private fun observeViewModelState() {
        binding.progressBar.isVisible = true
        viewModel.getDealLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is DealControlState.Loading -> {
                    showLoading()
                }

                is DealControlState.FetchAllItems -> {
                    hideLoading()
                    adapter.setDeals(state.items)
                }

                is DealControlState.Failure -> {
                    hideLoading()
                    showToast(state.throwable.message ?: "Something went wrong")
                }


            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE

    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    override fun onDealClick(deal: DealProductItemModel) {
        findNavController().navigate(
            R.id.action_dealListFragment_to_dealItemFragment, bundleOf(
                "id" to deal.id
            )
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }



}
