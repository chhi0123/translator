package com.jinil.nltranslator

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.jinil.nltranslator.ui.theme.NlTranslatorTheme
import com.jinil.nltranslator.view.LoadingState
import com.jinil.nltranslator.view.NlTranslatorViewModel
import com.jinil.nltranslator.view.NlTranslatorViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        window.setGravity(Gravity.BOTTOM)

        val inputText = getInputTextFromIntent()

        val viewModel = ViewModelProvider(
            this,
            NlTranslatorViewModelFactory()
        )[NlTranslatorViewModel::class.java]

        setContent {
            NlTranslatorTheme {
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent(
                        inputText = inputText,
                        viewModel = viewModel
                    )
                }
            }
        }

        onBackPressedDispatcher.addCallback {
            finish()
        }
    }

    private fun getInputTextFromIntent(): String {
        return if (intent.action == Intent.ACTION_PROCESS_TEXT) {
            intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT) ?: ""
        } else {
            ""
        }
    }
}

@Composable
fun AppContent(
    inputText: String = "",
    viewModel: NlTranslatorViewModel,
) {
    val loadingState by viewModel.loadingState.collectAsState()

    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(40.dp))
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .wrapContentSize()
            .background(Color.Transparent)) {
            when (loadingState) {
                is LoadingState.Loading -> {
                    CircularProgressIndicator(

                    )
                }

                is LoadingState.Loaded -> {
                    Text(text = inputText)
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

