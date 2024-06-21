package com.github.funczz.kotlin.rocket_launcher.android.activity.ready

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.github.funczz.kotlin.rocket_launcher.android.UiCommand
import com.github.funczz.kotlin.rocket_launcher.android.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.android.ui.theme.RocketLauncherTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReadyActivity : ComponentActivity() {

    @Inject
    lateinit var presenter: UiPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.render(
            output = presenter.stateFlow.value.addEvent("Ready!")
        )
        UiCommand.consumeEvent(
            uiState = presenter.stateFlow.value, context = applicationContext, presenter::render
        )
        setContent {
            RocketLauncherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.background
                ) {
                    ReadyScreen(
                        presenter = presenter,
                    )
                }
            }
        }
    }
}
