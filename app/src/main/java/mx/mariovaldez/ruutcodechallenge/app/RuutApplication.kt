package mx.mariovaldez.ruutcodechallenge.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import mx.mariovaldez.ruutcodechallenge.app.domain.usecases.InitializeDependencies

@HiltAndroidApp
class RuutApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        InitializeDependencies().invoke(
            context = applicationContext,
        )
    }
}