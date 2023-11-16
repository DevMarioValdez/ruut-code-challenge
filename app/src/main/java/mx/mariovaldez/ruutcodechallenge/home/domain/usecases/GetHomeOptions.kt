package mx.mariovaldez.ruutcodechallenge.home.domain.usecases

import dagger.Reusable
import mx.mariovaldez.ruutcodechallenge.home.presentation.models.HomeOption
import javax.inject.Inject

@Reusable
internal class GetHomeOptions @Inject constructor() {

    operator fun invoke(): List<HomeOption> = HomeOption.values().toList()
}
