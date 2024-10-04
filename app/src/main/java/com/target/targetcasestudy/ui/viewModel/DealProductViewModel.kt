package com.target.targetcasestudy.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.domain.usecase.ExecuteDealUseCase
import com.target.targetcasestudy.ui.state.DealControlState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealProductViewModel @Inject constructor(
    private val executeDealApiUseCase: ExecuteDealUseCase,

    ) : ViewModel() {

    private val _dealControlState = MutableSharedFlow<DealControlState>()
    fun getDealControlState() = _dealControlState.asSharedFlow()

    init {
       init()
    }

    internal fun init() {
        _dealControlState.tryEmit(DealControlState.Loading)
        getDealsApi()
    }

    private fun getDealsApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val dealList = executeDealApiUseCase.fetchAllDeals()
            dealList.onSuccess {
                _dealControlState.emit(DealControlState.FetchAllItems(it))
                Log.d(TAG, "list is $it")
            }.onFailure {
                _dealControlState.emit(DealControlState.Failure(throwable = it))
                Log.d(TAG, "error is $it")
            }
        }
    }

    internal fun fetchItembyId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _dealControlState.tryEmit(DealControlState.Loading)
            val dealList = executeDealApiUseCase.fetchDealById(id)
            dealList.onSuccess {
                _dealControlState.emit(DealControlState.FetchItemById(it))
                Log.d(TAG, "list is $it")
            }.onFailure {
                _dealControlState.emit(DealControlState.Failure(throwable = it))
                Log.d(TAG, "error is $it")
            }
        }
    }

    companion object {
        private const val TAG = "DealProductViewModel"
    }
}

