package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import dagger.hilt.android.AndroidEntryPoint


/*
class DealItemFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_deal_item, container, false)
  }
}
*/

@AndroidEntryPoint
class DealItemFragment : Fragment() {
    private lateinit var binding: FragmentDealItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDealItemBinding.inflate(inflater, container, false)
        return binding.root

    }
}

