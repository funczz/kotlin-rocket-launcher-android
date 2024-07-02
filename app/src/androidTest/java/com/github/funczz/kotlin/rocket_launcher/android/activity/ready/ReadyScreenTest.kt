package com.github.funczz.kotlin.rocket_launcher.android.activity.ready

import android.content.Context
import android.util.Log
import android_test.TestCountingWorkerFactory
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.funczz.kotlin.rocket_launcher.android.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.android.activity.counting.CountingActivity
import com.github.funczz.kotlin.rocket_launcher.android.di.BindModule
import com.github.funczz.kotlin.rocket_launcher.android.di.ProvideModule
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
class ReadyScreenTest {

    @Test
    fun init() {
        field.assertExists()
        button.assertTextEquals("start")
    }

    @Test
    fun startButtonClicked() {
        field.performTextInput("1")
        button.performClick()
        val actual = presenter.stateFlow.value
        assertEquals("", actual.input)
        assertEquals("1", actual.output)
        assertEquals(true, actual.samModel.isStarted)
        assertEquals(false, actual.samModel.isLaunched)
        assertEquals(false, actual.samModel.isAborted)
        assertEquals(true, actual.samModel.isTransitioned)
        assertEquals(CountingActivity::class.java, actual.events.first().payload)
    }

    @get:Rule
    val composeTestRule = createComposeRule()

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
            loggingLevel = Log.DEBUG
        )

        presenter.render(
            output = UiState()
        )

        composeTestRule.setContent {
            ReadyScreen(presenter = presenter)
        }
    }

}