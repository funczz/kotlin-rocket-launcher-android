package com.github.funczz.kotlin.rocket_launcher.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.github.funczz.kotlin.rocket_launcher.android.activity.aborted.AbortedActivity
import com.github.funczz.kotlin.rocket_launcher.android.activity.counting.CountingActivity
import com.github.funczz.kotlin.rocket_launcher.android.activity.launched.LaunchedActivity
import com.github.funczz.kotlin.rocket_launcher.android.activity.ready.ReadyActivity
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class UiPresenterImpl @Inject constructor(

    uiState: UiState

) : UiPresenter {

    private val mutableStateFlow: MutableStateFlow<UiState> = MutableStateFlow(
        uiState
    )

    override val stateFlow: StateFlow<UiState> = mutableStateFlow.asStateFlow()

    @Composable
    override fun getState(): State<UiState> = stateFlow.collectAsState()

    override fun render(output: RocketLauncherSamModel) {
        val uiState = mutableStateFlow.value.copy(samModel = output)
        val newUiState = if (uiState.samModel.isTransitioned) {
            val activityClass = when {
                RocketLauncherSamState.isReady(uiState.samModel) -> ReadyActivity::class.java

                RocketLauncherSamState.isCounting(uiState.samModel) -> CountingActivity::class.java

                RocketLauncherSamState.isLaunched(uiState.samModel) -> LaunchedActivity::class.java

                RocketLauncherSamState.isAborted(uiState.samModel) -> AbortedActivity::class.java

                else -> {
                    throw IllegalArgumentException("output=$output")
                }
            }
            uiState.addEvent(payload = activityClass).copy(input = "")
        } else {
            uiState
        }
        render(output = newUiState)
    }

    override fun render(output: UiState) {
        mutableStateFlow.value = output.update()
    }

    private fun UiState.update(): UiState {
        return this.copy(output = this.samModel.currentCounter.toString())
    }

}