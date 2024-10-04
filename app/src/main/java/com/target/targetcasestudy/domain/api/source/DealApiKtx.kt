package com.target.targetcasestudy.domain.api.source

import com.target.targetcasestudy.data.dto.Deal
import com.target.targetcasestudy.data.dto.DealResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DealApiKtx {

  @GET("deals")
  suspend fun retrieveDeals(): DealResponse

  @GET("deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: Int): Deal
}
