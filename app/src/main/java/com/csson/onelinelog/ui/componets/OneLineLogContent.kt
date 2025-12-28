package com.csson.onelinelog.ui.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csson.onelinelog.mvi.OneLineLogIntent
import com.csson.onelinelog.mvi.OneLineLogState
import java.time.LocalDate

@Composable
fun OneLineLogContent(
    state: OneLineLogState,
    onIntent: (OneLineLogIntent) -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Hello, OneLineLog App / ${LocalDate.now()}")
        Text("현재 입력된 로그 : ${state.writingText}")

        TextField(
            value = state.writingText,
            onValueChange = { input -> onIntent(OneLineLogIntent.OnTextChanged(input)) })

        Button(onClick = { onIntent(OneLineLogIntent.OnSave) }) {
            Text("저장")
        }

//        Text(text = "저장된 로그 : ${state.storedOneLineLog}")

        LazyColumn {
            items(state.oneLineLogs.count()) {
                val oneLineLog = state.oneLineLogs[it]
                Text("${oneLineLog.content} / ${oneLineLog.createdAt}")
            }
        }
    }
}