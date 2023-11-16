package mx.mariovaldez.ruutcodechallenge.stockdetail.data.models.remote.response

data class HistoryTrade(
    val lastSalePrice: Double,
    val lastSaleSize: Int,
    val lastSaleTime: Long,
    val lastUpdated: Long,
    val marketPercent: Int,
    val securityEvent: SecurityEvent,
    val symbol: String,
    val systemEvent: SystemEvent,
    val trades: List<Trade>,
    val volume: Int
)