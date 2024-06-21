package com.github.funczz.kotlin.rocket_launcher.android

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequest
import com.github.funczz.kotlin.rocket_launcher.android.worker.CountingWorker
import java.util.Optional
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

object UiCommand {

    fun enqueueWorker(uiState: UiState, render: (UiState) -> Unit) {
        if (uiState.request.isPresent) return
        Log.d(this::class.java.simpleName, "Enqueue worker: $uiState")
        val req = CountingWorker.newRequest()
        render(uiState.addEvent(req))
    }

    fun cancelWorker(uiState: UiState, render: (UiState) -> Unit) {
        val req = uiState.request.getOrNull() ?: return
        Log.d(this::class.java.simpleName, "Cancel worker: $uiState")
        render(uiState.addEvent(req.id))
    }

    fun consumeEvent(uiState: UiState, context: Context, render: (UiState) -> Unit) {
        val event = uiState.events.firstOrNull() ?: return
        Log.d(this::class.java.simpleName, "Consume event: $uiState")
        val newUiState = when (event.payload) {
            is Class<*> -> {
                context.startActivity(
                    newIntent(activityClass = event.payload, context = context)
                )
                uiState
            }

            is OneTimeWorkRequest -> {
                CountingWorker.enqueueUniqueWork(context = context, request = event.payload)
                uiState.copy(request = Optional.of(event.payload))
            }

            is UUID -> {
                if (event.payload == uiState.request.getOrNull()?.id) {
                    uiState.copy(request = Optional.empty(), isBreak = true)
                } else {
                    uiState
                }
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
        render(newUiState)
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