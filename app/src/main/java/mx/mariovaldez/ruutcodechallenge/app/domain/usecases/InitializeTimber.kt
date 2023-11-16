package mx.mariovaldez.ruutcodechallenge.app.domain.usecases

import android.content.Context
import mx.mariovaldez.ruutcodechallenge.BuildConfig
import timber.log.Timber

internal class InitializeTimber {

    operator fun invoke(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}