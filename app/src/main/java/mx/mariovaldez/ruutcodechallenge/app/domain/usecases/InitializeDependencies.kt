package mx.mariovaldez.ruutcodechallenge.app.domain.usecases

import android.content.Context

internal class InitializeDependencies {

    operator fun invoke(
        context: Context,
    ) {
        setupTimber(context)
    }

    private fun setupTimber(context: Context) {
        InitializeTimber().invoke(context)
    }
}