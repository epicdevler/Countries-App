package com.kulex.explorecountries.presentations.vms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kulex.explorecountries.data._models.Country
import com.kulex.explorecountries.data.repo.GetCountryUseCase
import com.kulex.explorecountries.data.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryVM : ViewModel() {

    val _uiState: MutableStateFlow<UiState<Country>> = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
    private val countryUseCase = GetCountryUseCase()


    fun getCountries() {
        viewModelScope.launch {
            _uiState.tryEmit(UiState(isLoading = true, msg = "Loading Countries..."))
            when (val result = countryUseCase.get()) {
                is Response.Success -> _uiState.tryEmit(
                    UiState(
                        isSuccess = true,
                        dataList = result.data!!.toMutableList(),
                        msg = result.msg
                    )
                )
                else -> _uiState.tryEmit(
                    UiState(
                        isError = true,
                        msg = result.msg
                    )
                )
            }
        }
    }
}

data class UiState<T>(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val msg: String? = null,
    val data: T? = null,
    val dataList: MutableList<T> = mutableListOf(),
)