package mx.mariovaldez.ruutcodechallenge.home.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.ruutcodechallenge.R
import mx.mariovaldez.ruutcodechallenge.databinding.ActivityHomeBinding
import mx.mariovaldez.yapecodechallenge.ktx.viewBinding

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by viewBinding(
        ActivityHomeBinding::inflate
    )
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    companion object {

        fun launch(from: Context) = from.startActivity(
            Intent(from, HomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }
}
