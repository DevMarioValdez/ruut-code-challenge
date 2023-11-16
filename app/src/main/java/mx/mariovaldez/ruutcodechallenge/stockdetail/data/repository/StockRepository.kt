package mx.mariovaldez.ruutcodechallenge.stockdetail.data.repository

import mx.mariovaldez.ruutcodechallenge.stockdetail.data.source.StockRemoteDataSource
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.mappers.HistoryTradeUIMapper
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.models.HistoryTradeUI
import javax.inject.Inject

internal class StockRepository @Inject constructor(
    private val remoteDataSource: StockRemoteDataSource,
    private val historyTradeUIMapper: HistoryTradeUIMapper,
) {

    suspend fun getStockHistory(symbol: String): List<HistoryTradeUI> =
        historyTradeUIMapper.map(remoteDataSource.getStockHistory(symbol))
}