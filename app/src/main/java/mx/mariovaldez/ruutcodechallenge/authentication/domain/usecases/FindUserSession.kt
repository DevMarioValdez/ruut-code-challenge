package mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases

import mx.mariovaldez.ruutcodechallenge.authentication.data.repository.SessionRepository
import javax.inject.Inject

internal class FindUserSession @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    operator fun invoke() = sessionRepository.findUserSession()
}