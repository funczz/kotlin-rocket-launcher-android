package com.github.funczz.kotlin.rocket_launcher.android.activity.counting

import android.util.Log
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.core.event.Abort
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting

object CountingCommand {

    fun abort(uiState: UiState, render: (RocketLauncherSamModel) -> Unit) {
        Log.i(this::class.java.simpleName, "`abort` button clicked: $uiState")
        val inputData = InputData(
            initialCounter = uiState.samModel.initialCounter,
            currentCounter = uiState.samModel.currentCounter,
            state = Counting,
            event = Abort
        )
        val rocketLauncherSamModel = uiState.samModel
        RocketLauncherSamAction.accept(
            input = inputData, present = rocketLauncherSamModel::present
        )
        Log.d(this::class.java.simpleName, "`abort` button clicked: [result] $uiState")
        render(rocketLauncherSamModel)
    }

}