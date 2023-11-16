package mx.mariovaldez.ruutcodechallenge.authentication.data.mappers

import dagger.Reusable
import mx.mariovaldez.ruutcodechallenge.authentication.domain.models.UserSession
import mx.mariovaldez.ruutcodechallenge.data.local.entities.LocalUser
import mx.mariovaldez.ruutcodechallenge.domain.mapper.Mapper
import javax.inject.Inject

@Reusable
internal class UserSessionMapper @Inject constructor() : Mapper<LocalUser, UserSession> {

    override fun map(value: LocalUser): UserSession =
        with(value) {
            UserSession(
                idUser = idUser,
                name = name,
                lastName = lastName,
                email = email,
                password = password,
            )
        }
}
