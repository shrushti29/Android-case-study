package com.target.targetcasestudy.data.repo

import com.target.targetcasestudy.data.mapper.toProductModelMapper
import com.target.targetcasestudy.domain.api.source.DealApiKtx
import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.domain.repo.DealRepository
import javax.inject.Inject

class DealRepositoryImpl @Inject constructor(private val dealApi: DealApiKtx) : DealRepository {
    override suspend fun getAllDealList(): List<DealProductItemModel> {
        return dealApi.retrieveDeals().deals.map {
            it.toProductModelMapper()
        }

    }

    override suspend fun getDealById(dealId: Int): DealProductItemModel {
        return dealApi.retrieveDeal(dealId).toProductModelMapper()
    }

}