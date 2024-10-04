package com.target.targetcasestudy.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.domain.usecase.ExecuteDealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
/*

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
}*/

@HiltViewModel
class DealProductViewModel @Inject constructor(private val executeDealApiUseCase: ExecuteDealUseCase) :
    ViewModel() {

    private val _dealList = MutableLiveData<List<DealProductItemModel>>()
    val dealList: LiveData<List<DealProductItemModel>> get() = _dealList

    init {
        getDealsApi()
    }

    private fun getDealsApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val dealList = executeDealApiUseCase.fetchAllDeals()
            dealList.onSuccess {
                _dealList.postValue(it)  
                Log.d(TAG, "list is $it")
            }.onFailure {
                Log.d(TAG, "error is $it")
            }
        }
    }

    companion object {
        private const val TAG = "DealProductViewModel"
    }
}

