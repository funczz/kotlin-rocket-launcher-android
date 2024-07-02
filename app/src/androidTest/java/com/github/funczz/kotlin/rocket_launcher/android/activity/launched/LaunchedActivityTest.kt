package com.github.funczz.kotlin.rocket_launcher.android.activity.launched

import android.content.Context
import android.content.Intent
import android_test.TestCountingWorkerFactory
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.funczz.kotlin.rocket_launcher.android.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.android.activity.ready.ReadyActivity
import com.github.funczz.kotlin.rocket_launcher.android.di.BindModule
import com.github.funczz.kotlin.rocket_launcher.android.di.ProvideModule
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Launched
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(BindModule::class, ProvideModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LaunchedActivityTest {

    @Test
    fun init() {
        field.assertTextEquals("Launched.")
        button.assertTextEquals("ready")
    }

    @Test
    fun readyButtonClicked() {
        button.performClick()
        val actual = presenter.stateFlow.value
        assertEquals("", actual.input)
        assertEquals("10", actual.output)
        assertEquals(false, actual.samModel.isStarted)
        assertEquals(false, actual.samModel.isLaunched)
        assertEquals(false, actual.samModel.isAborted)
        assertEquals(true, actual.samModel.isTransitioned)
        assertEquals(ReadyActivity::class.java, actual.events.first().payload)
    }

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    private lateinit var scenario: ActivityScenario<LaunchedActivity>

    private val field: SemanticsNodeInteraction
        get() = composeTestRule.onNodeWithTag("field")

    private val button: SemanticsNodeInteraction
        get() = composeTestRule.onNodeWithTag("button")

    private val context: Context
        get() = ApplicationProvider.getApplicationContext() //InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var presenter: UiPresenter

    @Before
    fun beforeEach() {
        hiltRule.inject()
        TestCountingWorkerFactory.initialize(
            context = context,
            presenter = presenter,
        )

        presenter.render(
            output = UiState(
                input = "",
                output = "0",
                samModel = RocketLauncherSamModel().apply {
                    present(
                        RocketLauncher(initialCounter = 1, currentCounter = 0, state = Launched)
                    )
                },
                currentActivityClass = LaunchedActivity::class.java,
            )
        )
        val intent = Intent(context, LaunchedActivity::class.java)
        scenario = ActivityScenario.launch(intent)
    }

}