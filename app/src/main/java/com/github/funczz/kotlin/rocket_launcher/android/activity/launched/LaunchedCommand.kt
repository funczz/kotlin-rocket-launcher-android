package com.github.funczz.kotlin.rocket_launcher.android.activity.launched

import android.util.Log
import com.github.funczz.kotlin.rocket_launcher.android.UiRepresentation
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.core.event.Initialize
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched

object LaunchedCommand {

    fun ready(uiState: UiState, render: (UiState) -> Unit) {
        Log.i(this::class.java.simpleName, "`ready` button clicked: $uiState")
        val inputData = InputData(
            initialCounter = uiState.samModel.initialCounter,
            currentCounter = uiState.samModel.currentCounter,
            state = Launched,
            event = Initialize,
        )
        RocketLauncherSamAction.accept(
            input = inputData, present = uiState.samModel::present
        )
        Log.d(this::class.java.simpleName, "`ready` button clicked: [result] $uiState")
        UiRepresentation.representation(model = uiState, render = render)
    }

}