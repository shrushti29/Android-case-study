package com.target.targetcasestudy.domain.api.extra

import com.target.targetcasestudy.data.dto.Deal
import com.target.targetcasestudy.data.dto.DealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path



interface DealApi {

  @GET("deals")
  fun retrieveDeals(): Call<DealResponse>

  @GET("deals/{dealId}")
  fun retrieveDeal(@Path("dealId") dealId: String): Call<Deal>
}
