package mx.mariovaldez.ruutcodechallenge.stockdetail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.ruutcodechallenge.companies.presentation.CompanyListViewModel
import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockUI
import mx.mariovaldez.ruutcodechallenge.stockdetail.domain.usecases.GetStockHistory
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.models.HistoryTradeUI
import javax.inject.Inject

@HiltViewModel
internal class StockDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getStockHistory: GetStockHistory,
) : ViewModel() {

    private val symbol = savedStateHandle.get<String>(SYMBOL).orEmpty()
    private val percent = savedStateHandle.get<Double>(PERCENT)
    private val price = savedStateHandle.get<Double>(PRICE)
    private val name = savedStateHandle.get<String>(NAME).orEmpty()
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Default)
    val state: StateFlow<State> get() = _state


    fun getPrice(): Double? = price
    fun getSymbol(): String = symbol
    fun getPercent(): Double? = percent
    fun getCompanyName(): String = name

    fun fetchData() {
        viewModelScope.launch {
            _state.value = State.Loading
            kotlin.runCatching {
                getStockHistory(symbol)
            }
                .onSuccess {
                    _state.value = State.Success(it[0])
                }
                .onFailure {
                    println("Error $it")
                    _state.value = State.Error

                }
        }
    }

    sealed class State {
        data object Loading : State()
        data class Success(val tradeUI: HistoryTradeUI) : State()
        data object Error : State()
        data object Default : State()
    }

    companion object {
        const val SYMBOL = "symbol_key"
        const val PRICE = "price_key"
        const val PERCENT = "percent_key"
        const val NAME = "name_key"
    }
}
