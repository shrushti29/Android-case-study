package com.target.targetcasestudy.ui.compose.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.usecase.ExecuteDealUseCase
import com.target.targetcasestudy.ui.state.DealControlState
import com.target.targetcasestudy.ui.state.DealItemControlState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealProductComposeViewModel @Inject constructor(
    private val executeDealApiUseCase: ExecuteDealUseCase,

    ) : ViewModel() {

    private val _dealStateFlow = MutableStateFlow<DealControlState>(DealControlState.Loading)
    private val _dealItemStateFlow =
        MutableStateFlow<DealItemControlState>(DealItemControlState.Loading)

    fun getDealStateFlow(): StateFlow<DealControlState> = _dealStateFlow.asStateFlow()
    fun getDealItemStateFlow(): StateFlow<DealItemControlState> = _dealItemStateFlow.asStateFlow()

    init {
        init()
    }

    private fun init() {
        getDealsApi()
    }

    private fun getDealsApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val dealList = executeDealApiUseCase.fetchAllDeals()
            dealList.onSuccess {
                _dealStateFlow.emit(DealControlState.FetchAllItems(it))
                Log.d(TAG, "list is $it")
            }.onFailure {
                _dealStateFlow.emit(DealControlState.Failure(throwable = it))
                Log.d(TAG, "error is $it")
            }
        }
    }

    internal fun fetchItemById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _dealItemStateFlow.emit(DealItemControlState.Loading)
            val dealList = executeDealApiUseCase.fetchDealById(id)
            dealList.onSuccess {
                _dealItemStateFlow.emit(DealItemControlState.FetchItemById(it))
                Log.d(TAG, "list is $it")
            }.onFailure {
                _dealItemStateFlow.emit(DealItemControlState.Failure(throwable = it))
                Log.d(TAG, "error is $it")
            }
        }
    }

    companion object {
        private const val TAG = "DealProductComposeViewModel"
    }
}

