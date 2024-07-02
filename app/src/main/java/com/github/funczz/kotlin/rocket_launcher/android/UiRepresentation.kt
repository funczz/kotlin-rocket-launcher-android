package com.github.funczz.kotlin.rocket_launcher.android

import com.github.funczz.kotlin.rocket_launcher.android.activity.aborted.AbortedActivity
import com.github.funczz.kotlin.rocket_launcher.android.activity.counting.CountingActivity
import com.github.funczz.kotlin.rocket_launcher.android.activity.launched.LaunchedActivity
import com.github.funczz.kotlin.rocket_launcher.android.activity.ready.ReadyActivity
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamState
import com.github.funczz.kotlin.sam.SamStateRepresentation

object UiRepresentation : SamStateRepresentation<UiState, UiState> {

    override fun representation(model: UiState): UiState {
        val newUiState = if (model.samModel.isTransitioned) {
            val activityClass = when {
                RocketLauncherSamState.isReady(model.samModel) -> {
                    model.countingState.initialize()
                    ReadyActivity::class.java
                }

                RocketLauncherSamState.isCounting(model.samModel) -> CountingActivity::class.java

                RocketLauncherSamState.isLaunched(model.samModel) -> LaunchedActivity::class.java

                RocketLauncherSamState.isAborted(model.samModel) -> AbortedActivity::class.java

                else -> {
                    throw IllegalArgumentException("model=$model")
                }
            }
            when (activityClass != model.currentActivityClass) {
                true -> {
                    model.addEvent(payload = activityClass).copy(
                        input = "",
                        currentActivityClass = activityClass,
                    )
                }

                else -> model
            }
        } else {
            model
        }
        return newUiState.copy(output = newUiState.samModel.currentCounter.toString())
    }

}