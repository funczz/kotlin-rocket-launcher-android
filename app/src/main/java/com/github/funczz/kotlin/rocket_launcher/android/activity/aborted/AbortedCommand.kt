package com.github.funczz.kotlin.rocket_launcher.android.activity.aborted

import android.util.Log
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.core.event.Initialize
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Aborted

object AbortedCommand {

    fun ready(uiState: UiState, render: (RocketLauncherSamModel) -> Unit) {
        Log.i(this::class.java.simpleName, "`ready` button clicked: $uiState")
        val inputData = InputData(
            initialCounter = uiState.samModel.initialCounter,
            currentCounter = uiState.samModel.currentCounter,
            state = Aborted,
            event = Initialize,
        )
        val rocketLauncherSamModel = uiState.samModel
        RocketLauncherSamAction.accept(
            input = inputData, present = rocketLauncherSamModel::present
        )
        Log.d(this::class.java.simpleName, "`ready` button clicked: [result] $uiState")
        render(rocketLauncherSamModel)
    }

}