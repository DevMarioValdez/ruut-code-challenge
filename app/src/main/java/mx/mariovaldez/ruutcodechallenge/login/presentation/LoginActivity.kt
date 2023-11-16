package mx.mariovaldez.ruutcodechallenge.login.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.mariovaldez.ruutcodechallenge.databinding.ActivityLoginBinding
import mx.mariovaldez.ruutcodechallenge.home.presentation.HomeActivity
import mx.mariovaldez.ruutcodechallenge.ktx.exhaustive
import mx.mariovaldez.ruutcodechallenge.ktx.gone
import mx.mariovaldez.ruutcodechallenge.ktx.invisible
import mx.mariovaldez.ruutcodechallenge.ktx.observe
import mx.mariovaldez.ruutcodechallenge.ktx.showLongLengthToast
import mx.mariovaldez.ruutcodechallenge.ktx.visible
import mx.mariovaldez.ruutcodechallenge.signup.presentation.SignUpActivity
import mx.mariovaldez.yapecodechallenge.ktx.viewBinding

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private val binding: ActivityLoginBinding by viewBinding(
        ActivityLoginBinding::inflate
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.state.observe(this, ::handle)
        viewModel.isSignInButtonEnabled.observe(this, ::setupEnableSignInButton)
    }

    private fun handle(state: LoginViewModel.State?) {
        when (state) {
            LoginViewModel.State.Error -> showError()
            LoginViewModel.State.Loading -> showProgress()
            LoginViewModel.State.Success -> navigateToHome()
            null -> {}
        }.exhaustive
    }

    private fun navigateToHome() {
        hideProgress()
        HomeActivity.launch(this)
        this.finish()
    }

    private fun showProgress() {
        with(binding) {
            signInButton.gone()
            progressBar.visible()
        }
    }

    private fun hideProgress() {
        with(binding) {
            signInButton.visible()
            progressBar.gone()
        }
    }

    private fun showError() {
        hideProgress()
        with(binding) {
            messageBoxTextView.visible()
        }
    }

    private fun hideError() {
        with(binding) {
            messageBoxTextView.invisible()
        }
    }

    private fun setupEnableSignInButton(value: Boolean) {
        binding.signInButton.isEnabled = value
    }

    private fun setupViews() {
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            signInButton.setOnClickListener {
                hideError()
                viewModel.logIn(
                    emailTextInputEditText.text.toString(),
                    passwordTextInputEditText.text.toString(),
                )
            }
            signUpButton.setOnClickListener {
                launchSignUp()
            }
            emailTextInputEditText.addTextChangedListener {
                validateCredencials()
            }
            passwordTextInputEditText.addTextChangedListener {
                validateCredencials()
            }
        }
    }

    private fun validateCredencials() {
        with(binding) {
            viewModel.validateCredentials(
                emailTextInputEditText.text.toString(),
                passwordTextInputEditText.text.toString(),
            )
        }
    }

    private fun launchSignUp() {
        lifecycleScope.launch {
            SignUpActivity.launch(this@LoginActivity)
        }
    }

    companion object {

        fun launch(from: Context) = from.startActivity(Intent(from, LoginActivity::class.java))
    }
}
