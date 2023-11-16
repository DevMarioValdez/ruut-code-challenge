package mx.mariovaldez.ruutcodechallenge.signup.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.ruutcodechallenge.R
import mx.mariovaldez.ruutcodechallenge.databinding.ActivitySignUpBinding
import mx.mariovaldez.ruutcodechallenge.ktx.exhaustive
import mx.mariovaldez.ruutcodechallenge.ktx.gone
import mx.mariovaldez.ruutcodechallenge.ktx.hideInputLayoutError
import mx.mariovaldez.ruutcodechallenge.ktx.observe
import mx.mariovaldez.ruutcodechallenge.ktx.showInputLayoutError
import mx.mariovaldez.ruutcodechallenge.ktx.showLongLengthToast
import mx.mariovaldez.ruutcodechallenge.ktx.visible
import mx.mariovaldez.yapecodechallenge.ktx.viewBinding

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val viewModel: SignUpViewModel by viewModels()
    private val binding: ActivitySignUpBinding by viewBinding(
        ActivitySignUpBinding::inflate
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.isSignUpButtonEnabled.observe(this, ::setupEnableSignUpButton)
        viewModel.state.observe(this, ::handle)
    }

    private fun handle(state: SignUpViewModel.State?) {
        when (state) {
            SignUpViewModel.State.Error -> showError()
            SignUpViewModel.State.Loading -> showProgress()
            SignUpViewModel.State.Success -> showSuccess()
            null -> {}
        }.exhaustive
    }

    private fun showProgress() {
        with(binding) {
            registerButton.gone()
            progressBar.visible()
        }
    }

    private fun hideProgress() {
        with(binding) {
            registerButton.visible()
            progressBar.gone()
        }
    }

    private fun showSuccess() {
        showLongLengthToast("Saved")
        this.finish()
    }

    private fun showError() {
        hideProgress()
        showLongLengthToast("Error")
    }

    private fun setupEnableSignUpButton(isEnabled: Boolean) =
        binding.registerButton.apply {
            this.isEnabled = isEnabled
        }


    private fun setupViews() {
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            nameTextInputEditText.addTextChangedListener {
                validateCredentials()
            }
            lastNameTextInputEditText.addTextChangedListener {
                validateCredentials()
            }
            emailTextInputEditText.addTextChangedListener {
                validateCredentials()
            }
            newPasswordTextInputEditText.addTextChangedListener {
                validateCredentials()
            }
            newPasswordConfirmedTextInputEditText.addTextChangedListener {
                if (viewModel.verifyPasswords(
                        newPasswordTextInputEditText.text.toString(),
                        newPasswordConfirmedTextInputEditText.text.toString()
                    )
                ) {
                    hideInputLayoutError(
                        newPasswordConfirmedTextInputEditText,
                        newPasswordConfirmedTextInputLayout,
                    )
                } else {
                    showInputLayoutError(
                        newPasswordConfirmedTextInputEditText,
                        newPasswordConfirmedTextInputLayout,
                        getString(R.string.password_req_not_does_not_match)
                    )
                }
                validateCredentials()
            }
            registerButton.setOnClickListener {
                registerUserAction()
            }

        }
    }

    private fun registerUserAction() {
        with(binding) {
            viewModel.registerUser(
                nameTextInputEditText.text.toString().trim(),
                lastNameTextInputEditText.text.toString().trim(),
                emailTextInputEditText.text.toString().trim(),
                newPasswordTextInputEditText.text.toString().trim()
            )
        }
    }

    private fun validateCredentials() {
        with(binding) {
            viewModel.validateForm(
                nameTextInputEditText.text.toString().trim(),
                lastNameTextInputEditText.text.toString().trim(),
                emailTextInputEditText.text.toString().trim(),
                newPasswordTextInputEditText.text.toString().trim(),
                newPasswordConfirmedTextInputEditText.text.toString().trim(),
            )
        }
    }

    companion object {

        fun launch(from: Context) = from.startActivity(Intent(from, SignUpActivity::class.java))
    }
}
