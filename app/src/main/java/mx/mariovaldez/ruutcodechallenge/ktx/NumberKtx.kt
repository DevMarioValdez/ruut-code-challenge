package mx.mariovaldez.ruutcodechallenge.ktx

import java.text.NumberFormat
import java.util.Locale

fun Double.formatMoneyCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("es", "MX")).format(this)
}

fun Double.formatToPercent(): String = "$this %"