package com.target.targetcasestudy.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.domain.model.PriceModel

@Composable
fun DetailsListScreen(dealItems: List<DealProductItemModel>, onItemClick: () -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(1.dp)) {
        items(dealItems.size) {
            DealListItem(dealItem = dealItems[it], onItemClick)
        }
    }

}


@Composable
fun DealListItem(dealItem: DealProductItemModel, onItemClick: () -> Unit) {

    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .clickable {

        }) {


        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = dealItem.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .size(140.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp))

            )
            val availabiltyString by remember {
                derivedStateOf {
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = Color(0xFF008300))) {
                            append("In stock")
                        }
                        withStyle(SpanStyle(color = Color(0xFF666666))) {
                            append(" in aisle ${dealItem.aisle}")
                        }
                    }
                }
            }
            Column(modifier = Modifier.padding(start = 16.dp)) {
                if (dealItem.salePrice != null) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = dealItem.salePrice.displayString,
                            fontSize = 21.sp,
                            color = Color(0xFFAA0000),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = "reg ${dealItem.regularPrice.displayString}",
                            fontSize = 12.sp,
                            color = Color(0xFF333333),
                        )
                    }
                } else {
                    Text(
                        text = dealItem.regularPrice.displayString,
                        fontSize = 21.sp,
                        color = Color(0xFFAA0000),
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = dealItem.fulfillment, fontSize = 12.sp, color = Color(0xFF666666)
                )

                Text(
                    text = dealItem.title, fontSize = 14.sp, color = Color(0xFF000000)
                )

                Text(text = availabiltyString, fontSize = 12.sp)
            }


        }
        HorizontalDivider(
            thickness = 1.dp, color = Color(0xFFD6D6D6), modifier = Modifier.padding(top = 16.dp)
        )
    }

}

@Preview
@Composable
fun PreviewItemList() {
    val dummy = DealProductItemModel(
        id = 0,
        title = "VIZIO D-Series 40\" Class 1080p Full-Array LED HD Smart TV",
        aisle = "b2",
        availability = "In stock",
        fulfillment = "Online",
        imageUrl = "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
        description = "fetch full product with details from https://api.target.com/mobile_case_study_deals/v1/deals/0",
        salePrice = PriceModel(
            amountInCents = 22999, currencySymbol = "$", displayString = "$229.99"
        ),
        regularPrice = PriceModel(
            amountInCents = 22999, currencySymbol = "$", displayString = "$229.99"
        )
    )
    val dummyList = listOf(dummy, dummy, dummy, dummy, dummy)
    DetailsListScreen(dummyList) {

    }
}