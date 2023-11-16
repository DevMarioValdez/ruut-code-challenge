package mx.mariovaldez.ruutcodechallenge.data.remote.services

import mx.mariovaldez.ruutcodechallenge.authentication.domain.models.Token
import mx.mariovaldez.ruutcodechallenge.data.remote.services.ApiServicesNames.CORE
import mx.mariovaldez.ruutcodechallenge.data.remote.services.ApiServicesNames.DATA
import mx.mariovaldez.ruutcodechallenge.data.remote.services.ApiServicesNames.LIST
import mx.mariovaldez.ruutcodechallenge.data.remote.services.ApiServicesNames.STOCK_COLLECTION
import mx.mariovaldez.ruutcodechallenge.companies.domain.models.Stock
import mx.mariovaldez.ruutcodechallenge.data.remote.services.ApiServicesNames.IEX_DEEP
import mx.mariovaldez.ruutcodechallenge.data.remote.services.ApiServicesNames.SYMBOL
import mx.mariovaldez.ruutcodechallenge.stockdetail.data.models.remote.response.HistoryTrade
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ApiServices {

    @GET("$DATA/$CORE/$STOCK_COLLECTION/$LIST")
    suspend fun getCompanies(
        @Query("collectionName") collectionName: String,
        @Query("token") token: Token
    ): List<Stock>

    @GET("$DATA/$CORE/$IEX_DEEP/{${SYMBOL}}")
    suspend fun getSymbolHistory(
        @Path(SYMBOL) symbol: String,
        @Query("token") token: Token
    ): List<HistoryTrade>
}
