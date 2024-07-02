package com.github.funczz.kotlin.rocket_launcher.android

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

object UiCommand {

    fun consumeEvent(
        uiState: UiState,
        context: Context,
        render: (UiState) -> Unit
    ) {
        val event = uiState.events.firstOrNull() ?: return
        Log.d(this::class.java.simpleName, "Consume event: $uiState")
        val newUiState = when (event.payload) {
            is Class<*> -> {
                context.startActivity(
                    newIntent(activityClass = event.payload, context = context)
                )
                uiState.copy(currentActivityClass = event.payload)
            }

            is String -> {
                Toast.makeText(context, event.payload, Toast.LENGTH_SHORT).show()
                uiState
            }

            else -> {
                throw IllegalArgumentException("event=$event")
            }
        }.delEvent(event = event)

        Log.d(this::class.java.simpleName, "Consume event: [result] $newUiState")
        UiRepresentation.representation(model = newUiState, render = render)
    }

    private fun newIntent(
        activityClass: Class<*>, context: Context, isStack: Boolean = false
    ): Intent {
        return Intent(context.applicationContext, activityClass).apply {
            if (!isStack) addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            )
        }
    }

}