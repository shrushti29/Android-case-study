package com.target.targetcasestudy.data.mapper

import com.target.targetcasestudy.data.dto.Deal
import com.target.targetcasestudy.data.dto.Price
import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.domain.model.PriceModel


fun Deal.toProductModelMapper(): DealProductItemModel {
    return DealProductItemModel(
        id = this.id,
        title = this.title,
        aisle = aisle,
        availability = availability,
        fulfillment = fulfillment,
        imageUrl = imageUrl,
        description = description,
        salePrice = salePrice?.toPriceMapper(),
        regularPrice = regularPrice.toPriceMapper()

    )
}

private fun Price.toPriceMapper(): PriceModel {
    return PriceModel(
        amountInCents = this.amountInCents,
        displayString = this.displayString,
        currencySymbol = this.currencySymbol
    )
}



