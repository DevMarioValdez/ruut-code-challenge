package mx.mariovaldez.ruutcodechallenge.companies.data.repository

import mx.mariovaldez.ruutcodechallenge.companies.data.source.CompaniesRemoteDataSource
import mx.mariovaldez.ruutcodechallenge.companies.presentation.mappers.StockUIMapper
import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockUI
import javax.inject.Inject

internal class CompaniesRepository @Inject constructor(
    private val remoteDataSource: CompaniesRemoteDataSource,
    private val stockUIMapper: StockUIMapper,
) {

    suspend fun getCompanies(collectionName: String): List<StockUI> = stockUIMapper.map(
        remoteDataSource.getCompanies(collectionName)
    )
}
