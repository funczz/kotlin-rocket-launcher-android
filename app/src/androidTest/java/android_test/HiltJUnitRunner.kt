package android_test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * == app/build.gradle.kts
 *
 * android {
 *     .
 *     .
 *     .
 *     android {
 *         defaultConfig {
 *             // Replace com.example.android.dagger with your class path.
 *             testInstrumentationRunner = "com.github.funczz.kotlin.rocket_launcher.android.HiltJUnitRunner"
 *         }
 *     }
 * }
 *
 * == test
 *
 * @UninstallModules(FooModule::class)
 * @HiltAndroidTest
 * @RunWith(AndroidJUnit4::class)
 * class FooBarTest {
 *     private val context: Context
 *         get() = ApplicationProvider.getApplicationContext() //InstrumentationRegistry.getInstrumentation().targetContext
 *
 *     @get:Rule
 *     var hiltRule = HiltAndroidRule(this)
 *
 *     @Before
 *     fun beforeEach() {
 *         hiltRule.inject()
 *         val config = Configuration.Builder()
 *             .setMinimumLoggingLevel(Log.DEBUG)
 *             .setExecutor(SynchronousExecutor())
 *             .build()
 *         WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
 *     }
 * }
 */

// A custom runner to set up the instrumented application class for tests.
class HiltJUnitRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}