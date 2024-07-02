package com.github.funczz.kotlin.rocket_launcher.android.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.github.funczz.kotlin.rocket_launcher.android.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.android.UiRepresentation
import com.github.funczz.kotlin.rocket_launcher.core.event.Decrement
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamState
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.UUID
import java.util.concurrent.TimeUnit

@HiltWorker
class CountingWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val presenter: UiPresenter,
) : Worker(context, workerParams) {

    private fun onSetUp() {
        Log.d(WORKER_TAG, "SetUp.")
    }

    private fun onIsContinue(): Boolean {
        val uiState = presenter.stateFlow.value
        val isBreak = uiState.countingState.isBreak
        val isCounting = RocketLauncherSamState.isCounting(presenter.stateFlow.value.samModel)
        Log.d(WORKER_TAG, "Continue: isBreak=%b isCounting=%b".format(isBreak, isCounting))
        return !isBreak && isCounting
    }

    private fun onDoWhile() {
        val uiState = presenter.stateFlow.value
        val samModel = uiState.samModel
        Log.d(WORKER_TAG, "While: [%d] %s".format(samModel.currentCounter, samModel.toString()))

        TimeUnit.SECONDS.sleep(1L)

        val inputData = InputData(
            initialCounter = samModel.initialCounter,
            currentCounter = samModel.currentCounter,
            state = Counting,
            event = Decrement,
        )
        RocketLauncherSamAction.accept(input = inputData, present = samModel::present)
        UiRepresentation.representation(model = uiState, render = presenter::render)
        Log.d(
            WORKER_TAG,
            "While: [%d:result] %s".format(inputData.currentCounter, samModel.toString())
        )
    }

    private fun onError(exception: Exception) {
        Log.e(WORKER_TAG, "Error.", exception)
        val uiState = presenter.stateFlow.value
        val newUiState = uiState.addEvent(exception.toString())
        UiRepresentation.representation(model = newUiState, render = presenter::render)
    }

    private fun onTearDown() {
        Log.d(WORKER_TAG, "TearDown.")
    }

    override fun doWork(): Result = try {
        onSetUp()
        while (onIsContinue()) onDoWhile()
        Result.success()
    } catch (e: Exception) {
        onError(exception = e)
        Result.failure()
    } finally {
        onTearDown()
    }

    companion object {
        @JvmStatic
        private val WORKER_NAME: String = CountingWorker::class.java.name

        @JvmStatic
        val WORKER_TAG: String = CountingWorker::class.java.simpleName

        @JvmStatic
        fun newRequest(): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<CountingWorker>().addTag(WORKER_TAG).build()

        @JvmStatic
        fun enqueueUniqueWork(
            context: Context,
            request: OneTimeWorkRequest? = null,
        ): Operation {
            val oneTimeWorkRequest = request ?: newRequest()
            return WorkManager.getInstance(context).enqueueUniqueWork(
                WORKER_NAME,
                ExistingWorkPolicy.KEEP,
                oneTimeWorkRequest,
            )
        }

        @JvmStatic
        @Suppress("unused")
        fun cancelWork(
            context: Context,
            request: OneTimeWorkRequest,
        ): Operation {
            return cancelWork(context = context, id = request.id)
        }

        @JvmStatic
        fun cancelWork(context: Context, id: UUID): Operation {
            return WorkManager.getInstance(context).cancelWorkById(id)
        }
    }

}