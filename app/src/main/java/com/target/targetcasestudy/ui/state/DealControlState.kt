package com.target.targetcasestudy.ui.state

import com.target.targetcasestudy.domain.model.DealProductItemModel

sealed interface DealControlState {
    object Loading : DealControlState
    data class FetchAllItems(val items: List<DealProductItemModel>) : DealControlState

    data class Failure(val throwable: Throwable) : DealControlState
}

sealed interface DealItemControlState {
    object Loading : DealItemControlState

    data class FetchItemById(val item: DealProductItemModel) : DealItemControlState

    data class Failure(val throwable: Throwable) : DealItemControlState
}


interface OnDealClickListener {
    fun onDealClick(deal: DealProductItemModel)
}
