package mx.mariovaldez.ruutcodechallenge.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.IsEmailValid
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.IsPasswordValid
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.SignIn
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val signIn: SignIn,
    private val isEmailValid: IsEmailValid,
    private val isPasswordValid: IsPasswordValid,
) : ViewModel() {

    private val _state: MutableStateFlow<State?> = MutableStateFlow(null)
    val state: StateFlow<State?> get() = _state

    private val _isSignInButtonEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSignInButtonEnabled: StateFlow<Boolean> get() = _isSignInButtonEnabled

    fun validateCredentials(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        _isSignInButtonEnabled.value =
            isEmailValid(email) && isPasswordValid(password)
    }

    fun logIn(
        email: String,
        password: String
    ) {

        viewModelScope.launch {
            _state.value = State.Loading
            kotlin.runCatching {
                signIn(email, password)
            }
                .onSuccess {
                    _state.value = if (it != null) {
                        State.Success
                    } else {
                        State.Error
                    }
                }
                .onFailure {
                    _state.value = State.Error
                }
        }
    }

    sealed class State {

        data object Loading : State()
        data object Success : State()
        data object Error : State()
    }
}