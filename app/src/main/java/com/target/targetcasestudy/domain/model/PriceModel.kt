package com.target.targetcasestudy.domain.model

data class PriceModel(
    val amountInCents: Int,
    val currencySymbol: String,
    val displayString: String
)