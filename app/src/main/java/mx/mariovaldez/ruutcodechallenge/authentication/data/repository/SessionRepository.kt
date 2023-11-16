package mx.mariovaldez.ruutcodechallenge.authentication.data.repository

import mx.mariovaldez.ruutcodechallenge.authentication.data.local.SessionLocalDataSource
import mx.mariovaldez.ruutcodechallenge.authentication.domain.models.UserSession
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionRepository @Inject constructor(
    private val sessionLocalDataSource: SessionLocalDataSource,
) {

    fun findUserSession(): UserSession? = sessionLocalDataSource.findUserSession()
}