package com.target.targetcasestudy.domain.api.extra

import com.target.targetcasestudy.data.dto.Deal
import com.target.targetcasestudy.data.dto.DealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal const val BASE_URL = "https://api.target.com/mobile_case_study_deals/v1/"

interface DealApi {

  @GET("deals")
  fun retrieveDeals(): Call<DealResponse>

  @GET("deals/{dealId}")
  fun retrieveDeal(@Path("dealId") dealId: String): Call<Deal>
}
