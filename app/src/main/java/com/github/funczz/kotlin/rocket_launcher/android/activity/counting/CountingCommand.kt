package com.github.funczz.kotlin.rocket_launcher.android.activity.counting

import android.content.Context
import android.util.Log
import com.github.funczz.kotlin.rocket_launcher.android.UiRepresentation
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.core.event.Abort
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting

object CountingCommand {

    fun start(uiState: UiState, context: Context) {
        Log.d(this::class.java.simpleName, "Start: $uiState")
        uiState.countingState.enqueueWork(uiState = uiState, context = context)
        Log.d(this::class.java.simpleName, "Start: [result] $uiState")
    }

    fun abort(uiState: UiState, context: Context, render: (UiState) -> Unit) {
        Log.i(this::class.java.simpleName, "`abort` button clicked: $uiState")
        uiState.countingState.cancelWork(context = context)
        val inputData = InputData(
            initialCounter = uiState.samModel.initialCounter,
            currentCounter = uiState.samModel.currentCounter,
            state = Counting,
            event = Abort
        )
        RocketLauncherSamAction.accept(
            input = inputData, present = uiState.samModel::present
        )
        Log.d(this::class.java.simpleName, "`abort` button clicked: [result] $uiState")
        UiRepresentation.representation(model = uiState, render = render)
    }

}