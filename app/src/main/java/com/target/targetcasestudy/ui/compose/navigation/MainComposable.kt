package com.target.targetcasestudy.ui.compose.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.target.targetcasestudy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable() {
    val navController = rememberNavController()
    val currentRoute = remember { mutableStateOf("") }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = when {
                destination.route == "List" -> "List"
                else -> "Details"
            }

        }
    }

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color(0xFFAA0000),
                actionIconContentColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent
            ),
            title = {
                Text(
                    text = currentRoute.value,
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 32.dp),
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    contentDescription = null,

                    )
            }
        )
    }) {
        it
        Column {
            AddNavGraph(navController)
        }


    }
}