package com.target.targetcasestudy.domain.model

data class DealProductItemModel (
    val id: Int,
    val title: String,
    val aisle: String,
    val availability: String,
    val fulfillment: String,
    val imageUrl: String?,
    val description: String,
    val salePrice: PriceModel? = null,
    val regularPrice: PriceModel
)
