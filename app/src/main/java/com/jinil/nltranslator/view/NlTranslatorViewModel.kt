package com.jinil.nltranslator.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class LoadingState {
    object Loading : LoadingState()
    data class Loaded(val data: String) : LoadingState()
    data class Toast(val data: String) : LoadingState()
}

class NlTranslatorViewModel : ViewModel() {
    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState: StateFlow<LoadingState> = _loadingState

    fun init(inputText: String) {

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.KOREAN)
            .build()
        val englishToKoreanTranslator = Translation.getClient(options)
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        englishToKoreanTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                englishToKoreanTranslator.translate(inputText)
                    .addOnSuccessListener {
                        viewModelScope.launch {
                            _loadingState.value = LoadingState.Toast(it)
                            delay(1000)
                            _loadingState.update { LoadingState.Loaded("Data Loaded!") }
                        }
                        englishToKoreanTranslator.close()
                    }
                    .addOnFailureListener {
                        viewModelScope.launch {
                            _loadingState.value = LoadingState.Toast(it.message ?: "")
                            delay(1000)
                            _loadingState.update { LoadingState.Loaded("Data Loaded!") }
                        }
                        englishToKoreanTranslator.close()
                    }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    _loadingState.value = LoadingState.Toast(it.message ?: "")
                    delay(1000)
                    _loadingState.update { LoadingState.Loaded("Data Loaded!") }
                }
                englishToKoreanTranslator.close()
            }
    }
}