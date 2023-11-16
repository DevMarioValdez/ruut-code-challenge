package mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.mappers

import mx.mariovaldez.ruutcodechallenge.domain.mapper.Mapper
import mx.mariovaldez.ruutcodechallenge.stockdetail.data.models.remote.response.Trade
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.models.TradeUI
import javax.inject.Inject

internal class TradeUIMapper @Inject constructor() : Mapper<Trade, TradeUI> {
    override fun map(value: Trade): TradeUI =
        with(value) {
            TradeUI(
                price,
                size,
                timestamp,
                tradeId
            )
        }
}