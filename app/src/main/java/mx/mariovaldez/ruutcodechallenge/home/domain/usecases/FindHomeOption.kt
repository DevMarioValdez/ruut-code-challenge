package mx.mariovaldez.ruutcodechallenge.home.domain.usecases

import dagger.Reusable
import mx.mariovaldez.ruutcodechallenge.home.presentation.models.HomeOption
import javax.inject.Inject

@Reusable
internal class FindHomeOption @Inject constructor() {

    operator fun invoke(id: String): HomeOption? = HomeOption.values().find { it.name == id }
}