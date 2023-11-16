package mx.mariovaldez.ruutcodechallenge.stockdetail.data.source

import mx.mariovaldez.ruutcodechallenge.data.remote.services.ApiServices
import mx.mariovaldez.ruutcodechallenge.stockdetail.data.models.remote.response.HistoryTrade
import mx.mariovaldez.ruutcodechallenge.util.Constants.token
import javax.inject.Inject

internal class StockRemoteDataSource @Inject constructor(
    private val apiServices: ApiServices,
) {

    suspend fun getStockHistory(symbol: String): List<HistoryTrade> =
        apiServices.getSymbolHistory(symbol, token)
}
