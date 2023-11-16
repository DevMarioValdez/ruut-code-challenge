package mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.models

data class HistoryTradeUI(
    val lastSalePrice: Double,
    val lastSaleSize: Int,
    val lastSaleTime: Long,
    val lastUpdated: Long,
    val marketPercent: Int,
    val symbol: String,
    val trades: List<TradeUI>,
    val volume: Int
)
