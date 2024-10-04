package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.ui.viewModel.DealProductViewModel
import dagger.hilt.android.AndroidEntryPoint


/*

@AndroidEntryPoint
class DealListFragment : Fragment() {
    private lateinit var binding: FragmentDealListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = DealItemAdapter()
        return binding.root

    }
}
*/


@AndroidEntryPoint
class DealListFragment : Fragment() {
    private lateinit var binding: FragmentDealListBinding
    private val viewModel: DealProductViewModel by viewModels()
    private lateinit var adapter: DealItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealListBinding.inflate(inflater, container, false)

        adapter = DealItemAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.dealList.observe(viewLifecycleOwner) { deals ->
            adapter.setDeals(deals)
        }

        return binding.root
    }
}
