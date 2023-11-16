package mx.mariovaldez.ruutcodechallenge.ktx

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

internal fun TextInputLayout.setInputTextLayoutError(string: String) {
    error = string
    errorIconDrawable = null
}

internal fun TextInputLayout.hideInputTextLayoutError() {
    error = null
}
