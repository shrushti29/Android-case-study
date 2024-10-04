package com.target.targetcasestudy.data.dto

import com.google.gson.annotations.SerializedName

data class DealResponse(
  @SerializedName("products")
  val deals: List<Deal>
)