package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import dagger.hilt.android.AndroidEntryPoint


/*
class DealListFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view =  inflater.inflate(R.layout.fragment_deal_list, container, false)

    view.findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(requireContext())
    view.findViewById<RecyclerView>(R.id.recycler_view).adapter = DealItemAdapter()

    return view
  }
}
*/




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
