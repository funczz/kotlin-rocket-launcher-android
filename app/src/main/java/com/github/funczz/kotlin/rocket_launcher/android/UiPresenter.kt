package com.github.funczz.kotlin.rocket_launcher.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import kotlinx.coroutines.flow.StateFlow

interface UiPresenter {

    val stateFlow: StateFlow<UiState>

    @Composable
    fun getState(): State<UiState>

    fun render(output: RocketLauncherSamModel)

    fun render(output: UiState)

}