package android_test

import com.github.funczz.kotlin.rocket_launcher.android.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.android.UiPresenterImpl
import com.github.funczz.kotlin.rocket_launcher.android.di.BindModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [BindModule::class]
)
abstract class FakeBindModule {

    @Singleton
    @Binds
    abstract fun bindUiPresenter(impl: UiPresenterImpl): UiPresenter

}