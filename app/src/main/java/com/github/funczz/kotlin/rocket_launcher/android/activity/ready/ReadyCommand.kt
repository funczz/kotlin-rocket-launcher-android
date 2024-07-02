package com.github.funczz.kotlin.rocket_launcher.android.activity.ready

import android.util.Log
import com.github.funczz.kotlin.rocket_launcher.android.UiRepresentation
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.core.event.Start
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready

object ReadyCommand {

    @JvmStatic
    fun updateFieldValue(value: String, uiState: UiState, render: (UiState) -> Unit) {
        Log.d(this::class.java.simpleName, "Input field changed: value=`$value`")
        try {
            val intString = if (value.isNotBlank()) {
                value.toInt().toString()
            } else ""
            val newUiState = uiState.copy(input = intString)
            UiRepresentation.representation(model = newUiState, render = render)
            Log.d(this::class.java.simpleName, "Input field updated: value=`$intString`")
        } catch (_: NumberFormatException) {
        }
    }

    @JvmStatic
    fun start(uiState: UiState, render: (UiState) -> Unit) {
        Log.i(this::class.java.simpleName, "`start` button clicked: $uiState")
        val counter = uiState.input.toInt()
        val inputData = InputData(
            initialCounter = 0,
            currentCounter = 0,
            state = Ready,
            event = Start(initialCounter = counter)
        )
        RocketLauncherSamAction.accept(
            input = inputData, present = uiState.samModel::present
        )
        Log.d(this::class.java.simpleName, "`start` button clicked: [result] $uiState")
        UiRepresentation.representation(model = uiState, render = render)
    }

}