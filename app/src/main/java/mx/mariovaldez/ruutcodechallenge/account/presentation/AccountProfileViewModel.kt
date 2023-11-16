package mx.mariovaldez.ruutcodechallenge.account.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.mariovaldez.ruutcodechallenge.authentication.domain.models.UserSession
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.FindUserSession
import javax.inject.Inject

@HiltViewModel
internal class AccountProfileViewModel @Inject constructor(
    private val findUserSession: FindUserSession,
) : ViewModel() {

    private var userSession: UserSession? = null

    fun getUserSession() = userSession

    private fun fetchUserSession() = findUserSession()

    init {
        userSession = fetchUserSession()
    }
}