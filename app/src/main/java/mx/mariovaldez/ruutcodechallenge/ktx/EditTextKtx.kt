package mx.mariovaldez.ruutcodechallenge.ktx

import android.widget.EditText
import mx.mariovaldez.ruutcodechallenge.R


internal fun EditText.showEditTextError() {
    setBackgroundResource(R.drawable.error_edit_text_background)
}

internal fun EditText.hideEditTextError() {
    setBackgroundResource(R.drawable.normal_edit_text_background)
}
