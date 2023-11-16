package mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases

import android.util.Patterns
import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class IsEmailValid @Inject constructor(){

    operator fun invoke(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}