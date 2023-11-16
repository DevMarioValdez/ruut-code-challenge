package mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases

import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class IsPasswordValid @Inject constructor() {

    operator fun invoke(password: String): Boolean =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_\\\\|+.:,;\'\"()!¡<>{}@/#^%\$?¿*\\[\\]~=])(?=\\S+$).{6,16}".toRegex()
            .matches(password)
}
