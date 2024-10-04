package com.target.targetcasestudy.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.usecase.ExecuteDealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealProductViewModel @Inject constructor(private val executeDealApiUseCase: ExecuteDealUseCase) :
    ViewModel() {
    init {
        getDealsApi()
    }

    fun getDealsApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val dealList = executeDealApiUseCase.fetchAllDeals()
            dealList.onSuccess {
                Log.d(TAG, "list is $it")
            }.onFailure {
                Log.d(TAG, "error is $it")
            }
        }
    }

    companion object{
        private const val TAG = "DealProductViewModel"
    }
}