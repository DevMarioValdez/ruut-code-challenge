package mx.mariovaldez.ruutcodechallenge.account.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.ruutcodechallenge.databinding.ActivityProfileBinding
import mx.mariovaldez.ruutcodechallenge.ktx.getFullNameFormat
import mx.mariovaldez.yapecodechallenge.ktx.viewBinding

@AndroidEntryPoint
class AccountProfileActivity : AppCompatActivity() {

    private val binding: ActivityProfileBinding by viewBinding(
        ActivityProfileBinding::inflate
    )
    private val viewModel: AccountProfileViewModel by viewModels()

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // implement logic here
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        val user = viewModel.getUserSession()
        with(binding) {
            fullNameTextView.text = user?.let {
                getFullNameFormat(
                    user.name,
                    user.lastName
                )
            }
            emailTextView.text = "${user?.email}"
            backButton.setOnClickListener {
                this@AccountProfileActivity.onBackPressedDispatcher.onBackPressed()
            }
        }

    }

    companion object {

        fun launch(from: Context) = from.startActivity(
            Intent(from, AccountProfileActivity::class.java)
        )
    }
}
