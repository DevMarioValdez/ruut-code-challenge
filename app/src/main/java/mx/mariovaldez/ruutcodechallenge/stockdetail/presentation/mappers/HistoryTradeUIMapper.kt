package mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.mappers

import mx.mariovaldez.ruutcodechallenge.domain.mapper.Mapper
import mx.mariovaldez.ruutcodechallenge.stockdetail.data.models.remote.response.HistoryTrade
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.models.HistoryTradeUI
import javax.inject.Inject

internal class HistoryTradeUIMapper @Inject constructor(
    private val tradeUIMapper: TradeUIMapper,
) : Mapper<HistoryTrade, HistoryTradeUI> {

    override fun map(value: HistoryTrade): HistoryTradeUI =
        with(value) {
            HistoryTradeUI(
                lastSalePrice,
                lastSaleSize,
                lastSaleTime,
                lastUpdated,
                marketPercent,
                symbol,
                tradeUIMapper.map(trades),
                volume,
            )
        }
}
