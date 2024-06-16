package com.github.funczz.kotlin.rocket_launcher.android.activity.counting

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.funczz.kotlin.rocket_launcher.android.R
import com.github.funczz.kotlin.rocket_launcher.android.ui.theme.RocketLauncherTheme

@Composable
fun CountingScreen(
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current

    val owner = LocalLifecycleOwner.current

    SideEffect { Log.i("CountingScreen", "content created") }

    Column {
        //count field
        BasicTextField(
            value = "N/A",
            onValueChange = {},
            modifier = modifier.testTag("field"),
            readOnly = true,
        )

        //abort button
        Button(
            onClick = {
            },
            modifier = modifier.testTag("button"),
            enabled = true,
        ) {
            Text(
                text = stringResource(id = R.string.abortButton),
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CountingPreview() {
    RocketLauncherTheme {
        CountingScreen(
        )
    }
}