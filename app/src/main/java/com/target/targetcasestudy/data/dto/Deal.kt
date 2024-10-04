package com.target.targetcasestudy.data.dto

import com.google.gson.annotations.SerializedName

data class Deal(
  @SerializedName("id") val id: Int,
  @SerializedName("title") val title: String,
  @SerializedName("aisle") val aisle: String,
  @SerializedName("image_url") val imageUrl: String,
  @SerializedName("fulfillment") val fulfillment: String,
  @SerializedName("availability") val availability: String,
  @SerializedName("regular_price") val regularPrice: Price,
  @SerializedName("description") val description: String,
  @SerializedName("sale_price") val salePrice: Price? = null,
)
