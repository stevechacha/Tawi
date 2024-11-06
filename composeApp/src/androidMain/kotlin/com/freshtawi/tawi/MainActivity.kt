package com.freshtawi.tawi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.freshtawi.tawi.app.AppScreenModel
import com.freshtawi.tawi.presentation.common.navigation.RootGraph
import com.freshtawi.tawi.presentation.common.resources.TawiTheme
import org.koin.android.ext.android.inject
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val appScreenModel: AppScreenModel by inject()
            val userLanguage by appScreenModel.language.collectAsState()

            TawiTheme(languageCode = userLanguage) {
                KoinContext {
                    RootGraph(navController)
                }
            }
        }
    }
}
