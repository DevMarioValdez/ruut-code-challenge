package mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases

import mx.mariovaldez.ruutcodechallenge.authentication.data.repository.AuthenticationLocalRepository
import javax.inject.Inject

internal class LogOut @Inject constructor(
    private val authenticationLocalRepository: AuthenticationLocalRepository,
){

    operator fun invoke() = authenticationLocalRepository.logout()
}