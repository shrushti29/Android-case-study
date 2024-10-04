package com.target.targetcasestudy.domain.repo

import com.target.targetcasestudy.domain.model.DealProductItemModel


interface DealRepository {
    suspend fun getAllDealList(): List<DealProductItemModel>

    suspend fun getDealById(dealId: Int): DealProductItemModel

}