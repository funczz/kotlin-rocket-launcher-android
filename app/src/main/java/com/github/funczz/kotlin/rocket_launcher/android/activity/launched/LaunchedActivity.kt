package com.github.funczz.kotlin.rocket_launcher.android.activity.launched


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.github.funczz.kotlin.rocket_launcher.android.ui.theme.RocketLauncherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchedActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketLauncherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedScreen(
                    )
                }
            }
        }
    }
}
