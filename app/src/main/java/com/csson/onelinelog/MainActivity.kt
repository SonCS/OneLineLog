package com.csson.onelinelog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csson.onelinelog.data.datastore.OneLineLogDataStore
import com.csson.onelinelog.data.datastore.OneLineLogsDataStore
import com.csson.onelinelog.data.repository.OneLineLogRepository
import com.csson.onelinelog.ui.screens.OneLineLogScreen
import com.csson.onelinelog.ui.theme.OneLineLogTheme
import com.csson.onelinelog.viewmodel.OneLineLogViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataStore = OneLineLogDataStore(applicationContext.OneLineLogsDataStore)
        val repository = OneLineLogRepository(dataStore)
        val viewModel = OneLineLogViewModel(repository)

        setContent {
            OneLineLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OneLineLogScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OneLineLogTheme {
        Greeting("Android")
    }
}