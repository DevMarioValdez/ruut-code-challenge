package mx.mariovaldez.ruutcodechallenge.companies.data.source

import mx.mariovaldez.ruutcodechallenge.data.remote.services.ApiServices
import mx.mariovaldez.ruutcodechallenge.companies.domain.models.Stock
import mx.mariovaldez.ruutcodechallenge.util.Constants.token
import javax.inject.Inject

internal class CompaniesRemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun getCompanies(
        collectionName: String,
    ): List<Stock> =
        apiServices.getCompanies(collectionName, token = token)
}
