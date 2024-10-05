package com.target.targetcasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.target.targetcasestudy.ui.compose.navigation.MainComposable
import dagger.hilt.android.AndroidEntryPoint

/**
* this is the main activity for compose screen Implementation
*
* */
@AndroidEntryPoint
class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainComposable()
        }


    }


}
