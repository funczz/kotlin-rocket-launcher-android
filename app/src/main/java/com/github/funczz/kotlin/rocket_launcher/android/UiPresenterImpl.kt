package com.github.funczz.kotlin.rocket_launcher.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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

    override fun render(output: UiState) {
        mutableStateFlow.value = output
    }

}