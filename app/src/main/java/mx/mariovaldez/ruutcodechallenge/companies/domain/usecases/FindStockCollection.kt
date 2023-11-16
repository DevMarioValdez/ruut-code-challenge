package mx.mariovaldez.ruutcodechallenge.companies.domain.usecases

import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockCollection
import javax.inject.Inject

internal class FindStockCollection @Inject constructor() {

    operator fun invoke(id: String): StockCollection? =
        StockCollection.values().find { it.name == id }
}