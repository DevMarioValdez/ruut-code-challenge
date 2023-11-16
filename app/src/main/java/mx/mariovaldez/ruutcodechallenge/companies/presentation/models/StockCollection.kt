package mx.mariovaldez.ruutcodechallenge.companies.presentation.models

import mx.mariovaldez.ruutcodechallenge.R

enum class StockCollection(val id: String, val value: String, val drawable: Int) {
    MOST_ACTIVE("mostactive", "Most Active", R.drawable.icv_most_active),
    GAINERS("gainers", "Gainers", R.drawable.icv_gainers),
    LOSERS("losers", "Losers", R.drawable.icv_rejected),
    IEX_VOLUME("iexvolume", "IEX Volume", R.drawable.icv_growth),
    IEX_PERCENT("iexpercent", "IEX Percent", R.drawable.icv_percent),
    PREMARKET_LOSERS("premarket_losers", "Pre Market Losers", R.drawable.icv_prediction),
    POST_MARKET_LOSERS("postmarket_losers", "Post Market Losers", R.drawable.icv_cost),
    PREMARKET_GAINERS("premarket_gainers", "Pre Market Gainers", R.drawable.icv_prediction),
    POST_MARKET_GAINERS("postmarket_gainers", "Post Market Gainers", R.drawable.icv_reward),
}
