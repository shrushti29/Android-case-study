package com.target.targetcasestudy.ui.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.ui.compose.viewModel.DealProductComposeViewModel
import com.target.targetcasestudy.ui.state.DealItemControlState


@SuppressLint("UnrememberedMutableState")
@Composable
fun ProductDetailScreen(id: Int?) {
    val viewModel: DealProductComposeViewModel = hiltViewModel()

    LaunchedEffect(key1 = id) {
        id?.let {
            viewModel.fetchItemById(id)
        }

    }
    val state = viewModel.getDealItemStateFlow().collectAsState().value
    val context = LocalContext.current
    var displayToast = remember {
        mutableStateOf(false)
    }

    Column(verticalArrangement = Arrangement.spacedBy(1.dp), modifier = Modifier.fillMaxSize()) {
        when (state) {
            is DealItemControlState.FetchItemById -> {
                ProductDetailItem(state.item) {
                    displayToast.value = true
                }
            }

            is DealItemControlState.Loading -> {
                Loader()

            }

            is DealItemControlState.Failure -> {

                ShowToast(
                    message = state.throwable.message ?: "Something went wrong",
                    context = context
                )

            }
        }
        if (displayToast.value) {

            ShowToast(message = "Hurray!! item added to the cart", context = context)

        }
    }


}

@Composable
fun ProductDetailItem(item: DealProductItemModel, onItemClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(bottom = 80.dp).
            verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = item.imageUrl, contentDescription = "", modifier = Modifier
                    .background(
                        color = Color.Transparent, shape = RoundedCornerShape(8.dp)
                    )
                    .size(343.dp)
                    .padding(horizontal = 16.dp)


            )

            if (item.salePrice != null) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = item.salePrice.displayString,
                        fontSize = 21.sp,
                        color = Color(0xFFAA0000),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = "reg ${item.regularPrice.displayString}",
                        fontSize = 12.sp,
                        color = Color(0xFF333333),
                    )
                }
            } else {
                Text(
                    text = item.regularPrice.displayString,
                    fontSize = 21.sp,
                    color = Color(0xFFAA0000),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Text(
                text = item.fulfillment,
                fontSize = 14.sp,
                color = Color(0xFF666666),
                modifier = Modifier.padding(start = 16.dp)
            )

            HorizontalDivider(
                thickness = 10.dp,
                color = Color(0x80666666),
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Product Details",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp, start = 16.dp)

            )
            Text(
                text = item.description,
                textAlign = TextAlign.Left,
                fontSize = 16.sp,
                color = Color(0xFF888888),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 2.dp)

            )

        }

        Button(
            onClick = { onItemClick() },
            colors = ButtonColors(
                containerColor = Color(0xFFAA0000),
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color(0xFFAA0000)
            ),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            contentPadding = PaddingValues(vertical = 8.dp)


        ) {
            Text(text = "Add to cart", color = Color.White, fontSize = 18.sp)

        }

    }
}

