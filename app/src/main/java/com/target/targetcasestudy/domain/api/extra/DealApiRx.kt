package com.target.targetcasestudy.domain.api.extra

import com.target.targetcasestudy.data.dto.Deal
import com.target.targetcasestudy.data.dto.DealResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DealApiRx {
    @GET("${BASE_URL}deals")
    fun retrieveDeals(): Single<DealResponse>

    @GET("${BASE_URL}deals/{dealId}")
    fun retrieveDeal(@Path("dealId") dealId: String): Single<Deal>
}
