package mx.mariovaldez.ruutcodechallenge.companies.domain.usecases

import kotlinx.coroutines.withContext
import mx.mariovaldez.ruutcodechallenge.companies.data.repository.CompaniesRepository
import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockUI
import mx.mariovaldez.ruutcodechallenge.domain.dispatchers.DefaultDispatcherProvider
import javax.inject.Inject

internal class GetCompanies @Inject constructor(
    private val repository: CompaniesRepository,
    private val defaultDispatcherProvider: DefaultDispatcherProvider,
) {

    suspend operator fun invoke(collectionName: String): List<StockUI> =
        withContext(defaultDispatcherProvider.default) {
            repository.getCompanies(collectionName)
        }
}
