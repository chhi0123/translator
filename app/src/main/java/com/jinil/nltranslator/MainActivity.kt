package com.jinil.nltranslator

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jinil.nltranslator.ui.theme.NlTranslatorTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NlTranslatorTheme {
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
        onBackPressedDispatcher.addCallback {
            finish()
        }
    }
}

@Composable
fun AppContent() {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(40.dp))
            .padding(40.dp)
    ) {
        Text(text = "first!")
        Spacer(modifier = Modifier
            .height(12.dp)
            .fillMaxWidth())
        Button(onClick = { /*TODO*/ }) {

        }
    }
}

