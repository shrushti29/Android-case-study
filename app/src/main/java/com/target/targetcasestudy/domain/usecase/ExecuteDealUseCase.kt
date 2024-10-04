package com.target.targetcasestudy.domain.usecase

import com.target.targetcasestudy.domain.model.DealProductItemModel

interface  ExecuteDealUseCase {
    suspend fun fetchAllDeals(): Result<List<DealProductItemModel>>
    suspend fun fetchDealById(dealId: Int): Result<DealProductItemModel>
}