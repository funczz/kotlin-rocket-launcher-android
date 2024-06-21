package android_test

import com.github.funczz.kotlin.rocket_launcher.android.UiState
import com.github.funczz.kotlin.rocket_launcher.android.di.ProvideModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ProvideModule::class]
)
object FakeProvideModule {

    //@Singleton
    @Provides
    fun provideUiState(): UiState = UiState()

}