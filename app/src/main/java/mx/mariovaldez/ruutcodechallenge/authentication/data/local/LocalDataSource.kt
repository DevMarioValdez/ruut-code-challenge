package mx.mariovaldez.ruutcodechallenge.authentication.data.local

import mx.mariovaldez.ruutcodechallenge.data.local.dao.AuthenticationDao
import mx.mariovaldez.ruutcodechallenge.data.local.dao.UserDao
import mx.mariovaldez.ruutcodechallenge.data.local.entities.LocalAuthentication
import mx.mariovaldez.ruutcodechallenge.data.local.entities.LocalUser
import mx.mariovaldez.ruutcodechallenge.ktx.getExpirationDate
import mx.mariovaldez.ruutcodechallenge.ktx.getToken
import mx.mariovaldez.ruutcodechallenge.ktx.getUUID
import javax.inject.Inject

internal class LocalDataSource @Inject constructor(
    private val userDao: UserDao,
    private val authentication: AuthenticationDao,
) {

    fun signIn(
        email: String,
        password: String,
    ): LocalUser? = userDao.observeUser(email, password)

    suspend fun signUp(
        name: String,
        lastName: String,
        email: String,
        password: String,
    ) {
        val id = getUUID()
        val expirationDate = getExpirationDate()
        userDao.saveUser(
            LocalUser(
                idUser = id,
                name = name,
                lastName = lastName,
                email = email,
                password = password,
            )
        )
        authentication.saveUserAuthentication(
            LocalAuthentication(
                id,
                "1231",
                expirationDate.toString()
            )
        )
    }

}