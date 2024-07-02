package com.github.funczz.kotlin.rocket_launcher.android.activity.launched

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.funczz.kotlin.rocket_launcher.android.R
import com.github.funczz.kotlin.rocket_launcher.android.UiCommand
import com.github.funczz.kotlin.rocket_launcher.android.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.android.ui.theme.RocketLauncherTheme
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LaunchedScreen(
    presenter: UiPresenter,
    modifier: Modifier = Modifier,
) {
    val uiState by presenter.getState()

    val context = LocalContext.current

    //val owner = LocalLifecycleOwner.current

    SideEffect { Log.d("LaunchedScreen", "Screen created: $uiState") }

    Column {
        //count field
        BasicTextField(
            value = "Launched.",
            onValueChange = {},
            modifier = modifier.testTag("field"),
            readOnly = true,
        )

        //ready button
        Button(
            onClick = {
                LaunchedCommand.ready(uiState = uiState, render = presenter::render)
            },
            modifier = modifier.testTag("button"),
            enabled = true,
        ) {
            Text(
                text = stringResource(id = R.string.readyButton),
            )
        }
    }

    UiCommand.consumeEvent(
        uiState = uiState,
        context = context,
        render = presenter::render
    )
}

@Preview(showBackground = true)
@Composable
fun LaunchedPreview() {
    RocketLauncherTheme {
        LaunchedScreen(
            presenter = object : UiPresenter {
                override val stateFlow: StateFlow<UiState>
                    get() = TODO("Not yet implemented")

                @Composable
                override fun getState(): State<UiState> {
                    TODO("Not yet implemented")
                }

                override fun render(output: UiState) {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}