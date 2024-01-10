package com.jinil.nltranslator.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoadingState {
    object Loading : LoadingState()
    data class Loaded(val data:String) : LoadingState()
}

class NlTranslatorViewModel : ViewModel() {
    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState: StateFlow<LoadingState> = _loadingState

    init {
        viewModelScope.launch {
            delay(1000)
            _loadingState.value = LoadingState.Loaded("Data Loaded!")
        }
    }
}