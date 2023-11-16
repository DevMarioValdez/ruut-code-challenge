package mx.mariovaldez.yapecodechallenge.ktx

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import mx.mariovaldez.ruutcodechallenge.ktx.hideKeyboard

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
