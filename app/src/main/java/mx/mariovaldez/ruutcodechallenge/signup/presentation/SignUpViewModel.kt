package mx.mariovaldez.ruutcodechallenge.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.IsEmailValid
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.IsPasswordValid
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.SignUp
import javax.inject.Inject

@HiltViewModel
internal class SignUpViewModel @Inject constructor(
    private val isEmailValid: IsEmailValid,
    private val isPasswordValid: IsPasswordValid,
    private val signUp: SignUp,
) : ViewModel() {

    private val _state: MutableStateFlow<State?> = MutableStateFlow(null)
    val state: StateFlow<State?> get() = _state

    private val _isSignUpButtonEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSignUpButtonEnabled: StateFlow<Boolean> get() = _isSignUpButtonEnabled

    fun validateForm(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) = viewModelScope.launch {
        _isSignUpButtonEnabled.value =
            isEmailValid(email) && isPasswordValid(password) && isPasswordValid(confirmPassword) && name.isNotEmpty() && lastName.isNotEmpty()
    }

    fun verifyPasswords(
        password: String,
        confirmPassword: String
    ) = password == confirmPassword

    fun registerUser(
        name: String,
        lastName: String,
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                signUp(
                    name, lastName, email, password
                )
            }
                .onSuccess {
                    println("Succeess")
                    _state.value = State.Success
                }
                .onFailure {
                    println(" failure ${it.message}")
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
