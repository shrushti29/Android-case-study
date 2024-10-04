package com.target.targetcasestudy.domain.usecase

import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.domain.repo.DealRepository
import retrofit2.HttpException
import javax.inject.Inject

class ExecuteDealApiUseCaseImpl @Inject constructor(
    private val dealRepository: DealRepository
) : ExecuteDealUseCase {
    override suspend fun fetchAllDeals(): Result<List<DealProductItemModel>> {
        return try {
            Result.success(dealRepository.getAllDealList())
        } catch (httpException: HttpException) {
            Result.failure(RuntimeException("${httpException.code()} : ${httpException.message()}"))

        } catch (exception: Exception) {
            Result.failure(RuntimeException("Oops!! Something went wrong"))
        }
    }

    override suspend fun fetchDealById(dealId: Int): Result<DealProductItemModel> {
        return try {
            Result.success(dealRepository.getDealById(dealId))
        } catch (httpException: HttpException) {
            Result.failure(RuntimeException("${httpException.code()} : ${httpException.message()}"))

        } catch (exception: Exception) {
            Result.failure(RuntimeException("Oops!! Something went wrong"))
        }
    }
}