package com.target.targetcasestudy.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.usecase.ExecuteDealUseCase
import com.target.targetcasestudy.ui.state.DealControlState
import com.target.targetcasestudy.ui.state.DealItemControlState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealProductViewModel @Inject constructor(
    private val executeDealApiUseCase: ExecuteDealUseCase,

    ) : ViewModel() {

    private val _dealLiveData = MutableLiveData<DealControlState>()
    private val _dealItemLiveData = MutableLiveData<DealItemControlState>()
    fun getDealLiveData():LiveData<DealControlState> = _dealLiveData
    fun getDealItemLiveData():LiveData<DealItemControlState> = _dealItemLiveData

    init {
        getDealsApi()
    }



     fun getDealsApi() {
        _dealLiveData.postValue(DealControlState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val dealList = executeDealApiUseCase.fetchAllDeals()
            dealList.onSuccess {
                _dealLiveData.postValue(DealControlState.FetchAllItems(it))
                Log.d(TAG, "list is $it")
            }.onFailure {
                _dealLiveData.postValue(DealControlState.Failure(throwable = it))
                Log.d(TAG, "error is $it")
            }
        }
    }

    internal fun fetchItemById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _dealItemLiveData.postValue(DealItemControlState.Loading)
            val dealList = executeDealApiUseCase.fetchDealById(id)
            dealList.onSuccess {
                _dealItemLiveData.postValue(DealItemControlState.FetchItemById(it))
                Log.d(TAG, "list is $it")
            }.onFailure {
                _dealItemLiveData.postValue(DealItemControlState.Failure(throwable = it))
                Log.d(TAG, "error is $it")
            }
        }
    }

    companion object {
        private const val TAG = "DealProductViewModel"
    }
}

