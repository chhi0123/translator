package com.jinil.nltranslator.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NlTranslatorViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NlTranslatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NlTranslatorViewModel() as T
        }
        throw IllegalAccessException("unknown viewModel class")
    }
}