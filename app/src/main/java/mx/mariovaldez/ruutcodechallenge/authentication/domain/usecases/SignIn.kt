package mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases

import kotlinx.coroutines.withContext
import mx.mariovaldez.ruutcodechallenge.authentication.data.repository.AuthenticationLocalRepository
import mx.mariovaldez.ruutcodechallenge.authentication.domain.models.UserSession
import mx.mariovaldez.ruutcodechallenge.domain.dispatchers.DefaultDispatcherProvider
import javax.inject.Inject

internal class SignIn @Inject constructor(
    private val authenticationLocalRepository: AuthenticationLocalRepository,
    private val defaultDispatcherProvider: DefaultDispatcherProvider,
) {

    suspend operator fun invoke(email: String, password: String): UserSession? =
        withContext(defaultDispatcherProvider.default) {
            authenticationLocalRepository.signIn(email, password)
        }
}
