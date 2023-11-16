package mx.mariovaldez.ruutcodechallenge.authentication.domain.models

internal data class UserSession(
    val idUser: String,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
)