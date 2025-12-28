package com.csson.onelinelog.ui.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.csson.onelinelog.mvi.OneLineLogSideEffect
import com.csson.onelinelog.ui.componets.OneLineLogContent
import com.csson.onelinelog.viewmodel.OneLineLogViewModel

@Composable
fun OneLineLogScreen(viewModel: OneLineLogViewModel, modifier: Modifier) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsState()

    // SideEffect 구독
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            val toastMessage = when (effect) {
                is OneLineLogSideEffect.ShowSavedToast -> "저장이 되었습니다."
                is OneLineLogSideEffect.ShowToast -> effect.message
                else -> ""
            }

            if (toastMessage.isNotEmpty()) {
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    OneLineLogContent(state = state, onIntent = viewModel::onIntent, modifier = modifier)
}