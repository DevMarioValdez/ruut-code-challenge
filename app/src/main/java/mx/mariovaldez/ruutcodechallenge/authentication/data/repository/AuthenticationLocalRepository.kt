package mx.mariovaldez.ruutcodechallenge.authentication.data.repository

import mx.mariovaldez.ruutcodechallenge.authentication.data.local.LocalDataSource
import mx.mariovaldez.ruutcodechallenge.authentication.data.local.SessionLocalDataSource
import mx.mariovaldez.ruutcodechallenge.authentication.data.mappers.UserSessionMapper
import mx.mariovaldez.ruutcodechallenge.authentication.domain.models.UserSession
import javax.inject.Inject

internal class AuthenticationLocalRepository @Inject constructor(
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: UserSessionMapper,
) {

    fun signIn(
        email: String,
        password: String,
    ): UserSession? {
        val signInResponse = localDataSource.signIn(email, password)

        return signInResponse?.let {
            mapper.map(it).also {
                with(sessionLocalDataSource) {
                    saveUserSession(userSession = mapper.map(signInResponse))
                    saveToken("") // this functionality is going to be implemented when we have an remote auth
                }
            }
        }
    }

    suspend fun signUp(
        name: String,
        lastName: String,
        email: String,
        password: String,
    ) {
        localDataSource.signUp(
            name, lastName, email, password
        )
    }

    fun logout() = sessionLocalDataSource.logout()
}
