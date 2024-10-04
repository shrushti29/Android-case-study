package com.target.targetcasestudy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.ui.DealListFragment
import com.target.targetcasestudy.ui.viewModel.DealProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private val dealProductViewModel: DealProductViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
   dealProductViewModel.getDealsApi()
    supportFragmentManager.beginTransaction()
      .replace(R.id.container, DealListFragment())
      .commit()
  }
}
