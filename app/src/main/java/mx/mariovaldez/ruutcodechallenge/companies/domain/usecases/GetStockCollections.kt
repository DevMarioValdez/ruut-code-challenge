package mx.mariovaldez.ruutcodechallenge.companies.domain.usecases

import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockCollection
import javax.inject.Inject

internal class GetStockCollections @Inject constructor() {

    operator fun invoke(): List<StockCollection> = StockCollection.values().toList()
}