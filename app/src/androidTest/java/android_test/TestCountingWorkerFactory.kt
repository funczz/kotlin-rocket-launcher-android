package android_test

import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.github.funczz.kotlin.rocket_launcher.android.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.android.worker.CountingWorker

class TestCountingWorkerFactory(

    private val presenter: UiPresenter,

    ) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            CountingWorker::class.java.name -> CountingWorker(
                context = appContext,
                workerParams = workerParameters,
                presenter = presenter,
            )

            else -> null
        }
    }

    companion object {

        @JvmStatic
        fun initialize(
            context: Context,
            presenter: UiPresenter,
            loggingLevel: Int = Log.DEBUG,
        ) {
            val config = Configuration.Builder()
                .setMinimumLoggingLevel(loggingLevel)
                .setExecutor(SynchronousExecutor())
                .setWorkerFactory(
                    TestCountingWorkerFactory(
                        presenter = presenter,
                    )
                )
                .build()
            WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
        }

    }
}