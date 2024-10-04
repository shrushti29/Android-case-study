package com.target.targetcasestudy.domain.repo

import com.target.targetcasestudy.data.dto.Deal
import com.target.targetcasestudy.data.dto.DealResponse
import com.target.targetcasestudy.domain.model.DealProductItemModel
import retrofit2.Call


interface DealRepository {
    suspend fun getAllDealList(): List<DealProductItemModel>

    suspend fun getDealById(dealId: Int): DealProductItemModel

}