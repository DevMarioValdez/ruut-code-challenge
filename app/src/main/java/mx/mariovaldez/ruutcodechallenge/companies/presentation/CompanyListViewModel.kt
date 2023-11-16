package mx.mariovaldez.ruutcodechallenge.companies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.FindUserSession
import mx.mariovaldez.ruutcodechallenge.authentication.domain.usecases.LogOut
import mx.mariovaldez.ruutcodechallenge.companies.domain.usecases.FindStockCollection
import mx.mariovaldez.ruutcodechallenge.companies.domain.usecases.GetCompanies
import mx.mariovaldez.ruutcodechallenge.companies.presentation.mappers.HomeOptionUIMapper
import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockUI
import mx.mariovaldez.ruutcodechallenge.home.domain.usecases.FindHomeOption
import mx.mariovaldez.ruutcodechallenge.home.domain.usecases.GetHomeOptions
import mx.mariovaldez.ruutcodechallenge.home.presentation.models.HomeOption
import mx.mariovaldez.ruutcodechallenge.home.presentation.models.OptionUI
import javax.inject.Inject

@HiltViewModel
internal class CompanyListViewModel @Inject constructor(
    private val getCompanies: GetCompanies,
    private val findUserSession: FindUserSession,
    private val getHomeOptions: GetHomeOptions,
    private val homeOptionUIMapper: HomeOptionUIMapper,
    private val findStockCollections: FindStockCollection,
    private val findHomeOption: FindHomeOption,
    private val logOut: LogOut,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Default)
    val state: StateFlow<State> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    private val _userName: MutableStateFlow<String> = MutableStateFlow("")
    val userName: StateFlow<String> get() = _userName

    private var collections: List<StockUI> = emptyList()

    fun fetchData(collectionName: String) {
        viewModelScope.launch {
            val collection = findStockCollections(collectionName)?.id.toString()
            _state.value = State.Loading
            kotlin.runCatching {
                getCompanies(collection)
            }
                .onSuccess {
                    println(it)
                    collections = it
                    _state.value = State.Success(it)
                }
                .onFailure {
                    println(it)
                    _state.value = State.Error
                }
        }
    }

    fun getCollections() = collections
    fun getOptions(): List<OptionUI> = homeOptionUIMapper.map(getHomeOptions())
    fun setDefaultState() {
        _state.value = State.Default
    }

    fun logout() {
        viewModelScope.launch {
            logOut
        }
    }

    fun optionClicked(id: String) {
        val homeOption = findHomeOption(id)
        viewModelScope.launch {
            delay(OPTION_CLICKED_DELAY)
            _event.emit(homeOption.toEvent())
        }
    }

    init {
        viewModelScope.launch {
            _userName.value = findUserSession()?.name.toString()
        }
    }

    private fun HomeOption?.toEvent(): Event = when (this) {
        HomeOption.ACCOUNT -> Event.NavigateToAccountProfile

        else -> Event.ShowError("")
    }

    sealed class Event {
        data object NavigateToAccountProfile : Event()
        data class ShowError(val message: String) : Event()

    }

    sealed class State {

        data object Loading : State()
        data class Success(val collections: List<StockUI>) : State()
        data object Error : State()
        data object Default : State()
    }

    companion object {

        private const val OPTION_CLICKED_DELAY: Long = 300
    }
}
