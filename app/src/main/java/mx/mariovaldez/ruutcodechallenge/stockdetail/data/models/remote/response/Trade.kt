package mx.mariovaldez.ruutcodechallenge.stockdetail.data.models.remote.response

data class Trade(
    val isISO: Boolean,
    val isOddLot: Boolean,
    val isOutsideRegularHours: Boolean,
    val isSinglePriceCross: Boolean,
    val isTradeThroughExempt: Boolean,
    val price: Double,
    val size: Int,
    val timestamp: Long,
    val tradeId: Long
)