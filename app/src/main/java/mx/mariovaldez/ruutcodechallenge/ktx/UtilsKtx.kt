package mx.mariovaldez.ruutcodechallenge.ktx

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

internal fun showInputLayoutError(
    editText: EditText,
    textInputLayout: TextInputLayout,
    message: String,
) {
    textInputLayout.setInputTextLayoutError(message)
    editText.showEditTextError()
}

internal fun hideInputLayoutError(editText: EditText, textInputLayout: TextInputLayout) {
    textInputLayout.hideInputTextLayoutError()
    editText.hideEditTextError()
}