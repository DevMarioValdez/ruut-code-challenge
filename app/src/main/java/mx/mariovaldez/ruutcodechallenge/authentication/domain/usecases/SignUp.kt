package mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases

import kotlinx.coroutines.withContext
import mx.mariovaldez.ruutcodechallenge.authentication.data.repository.AuthenticationLocalRepository
import mx.mariovaldez.ruutcodechallenge.domain.dispatchers.DefaultDispatcherProvider
import javax.inject.Inject

internal class SignUp @Inject constructor(
    private val authenticationLocalRepository: AuthenticationLocalRepository,
    private val defaultDispatcherProvider: DefaultDispatcherProvider,
) {

    suspend operator fun invoke(
        name: String,
        lastName: String,
        email: String,
        password: String,
    ) {
        withContext(defaultDispatcherProvider.default) {
            authenticationLocalRepository.signUp(
                name, lastName, email, password
            )
        }
    }
}