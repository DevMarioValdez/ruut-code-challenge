package mx.mariovaldez.ruutcodechallenge.companies.presentation.mappers

import dagger.Reusable
import mx.mariovaldez.ruutcodechallenge.companies.domain.models.Stock
import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockUI
import mx.mariovaldez.ruutcodechallenge.domain.mapper.Mapper
import javax.inject.Inject

@Reusable
internal class StockUIMapper @Inject constructor() : Mapper<Stock, StockUI> {

    override fun map(value: Stock): StockUI = with(value) {
        StockUI(
            avgTotalVolume,
            calculationPrice,
            change,
            changePercent,
            close,
            closeSource,
            closeTime,
            companyName,
            currency,
            delayedPrice,
            delayedPriceTime,
            extendedChange,
            extendedChangePercent,
            extendedPrice,
            extendedPriceTime,
            high,
            highTime,
            iexAskSize,
            iexBidSize,
            iexClose,
            iexCloseTime,
            iexLastUpdated,
            iexMarketPercent,
            iexOpen,
            iexOpenTime,
            iexRealtimePrice,
            iexRealtimeSize,
            iexVolume,
            isUSMarketOpen,
            lastTradeTime,
            latestPrice,
            latestSource,
            latestTime,
            latestUpdate,
            latestVolume,
            low,
            lowTime,
            marketCap,
            oddLotDelayedPrice,
            oddLotDelayedPriceTime,
            open,
            openSource,
            openTime,
            peRatio,
            previousClose,
            previousVolume,
            primaryExchange,
            symbol,
            volume,
            week52High,
            week52Low,
            ytdChange
        )
    }
}
