package mx.mariovaldez.ruutcodechallenge.splash.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.mariovaldez.ruutcodechallenge.R
import mx.mariovaldez.ruutcodechallenge.home.presentation.HomeActivity
import mx.mariovaldez.ruutcodechallenge.login.presentation.LoginActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(LAUNCH_DELAY)
            launchLogin()
        }
    }

    private fun launchLogin() {
        LoginActivity.launch(this)
        finish()
    }

    companion object {

        private const val LAUNCH_DELAY: Long = 1700
    }
}
