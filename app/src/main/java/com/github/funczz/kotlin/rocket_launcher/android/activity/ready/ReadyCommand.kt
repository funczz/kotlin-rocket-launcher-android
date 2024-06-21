package com.github.funczz.kotlin.rocket_launcher.android.activity.ready

import android.util.Log
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.core.event.Start
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready

object ReadyCommand {

    @JvmStatic
    fun updateFieldValue(value: String, uiState: UiState, render: (UiState) -> Unit) {
        Log.d(this::class.java.simpleName, "Input field changed: value=`$value`")
        try {
            val intString = if (value.isNotBlank()) {
                value.toInt().toString()
            } else ""
            render(uiState.copy(input = intString))
            Log.d(this::class.java.simpleName, "Input field updated: value=`$intString`")
        } catch (_: NumberFormatException) {
        }
    }

    @JvmStatic
    fun start(uiState: UiState, render: (RocketLauncherSamModel) -> Unit) {
        Log.i(this::class.java.simpleName, "`start` button clicked: $uiState")
        val counter = uiState.input.toInt()
        val inputData = InputData(
            initialCounter = 0,
            currentCounter = 0,
            state = Ready,
            event = Start(initialCounter = counter)
        )
        val rocketLauncherSamModel = uiState.samModel
        RocketLauncherSamAction.accept(
            input = inputData, present = rocketLauncherSamModel::present
        )
        Log.d(this::class.java.simpleName, "`start` button clicked: [result] $uiState")
        render(rocketLauncherSamModel)
    }

}