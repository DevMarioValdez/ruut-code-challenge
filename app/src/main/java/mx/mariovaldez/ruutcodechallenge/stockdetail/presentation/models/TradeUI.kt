package mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.models

data class TradeUI(
    val price: Double,
    val size: Int,
    val timestamp: Long,
    val tradeId: Long
)
