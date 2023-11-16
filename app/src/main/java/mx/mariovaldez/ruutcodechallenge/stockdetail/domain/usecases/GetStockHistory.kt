package mx.mariovaldez.ruutcodechallenge.stockdetail.domain.usecases

import kotlinx.coroutines.withContext
import mx.mariovaldez.ruutcodechallenge.domain.dispatchers.DefaultDispatcherProvider
import mx.mariovaldez.ruutcodechallenge.stockdetail.data.repository.StockRepository
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.models.HistoryTradeUI
import javax.inject.Inject

internal class GetStockHistory @Inject constructor(
    private val repository: StockRepository,
    private val dispatcherProvider: DefaultDispatcherProvider,
) {

    suspend operator fun invoke(symbol: String): List<HistoryTradeUI> =
        withContext(dispatcherProvider.default) {
            repository.getStockHistory(symbol)
        }
}
