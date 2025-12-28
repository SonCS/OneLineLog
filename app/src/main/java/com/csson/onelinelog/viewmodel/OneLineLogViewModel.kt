package com.csson.onelinelog.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csson.onelinelog.mvi.OneLineLogIntent
import com.csson.onelinelog.mvi.OneLineLogSideEffect
import com.csson.onelinelog.mvi.OneLineLogState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OneLineLogViewModel: ViewModel() {

    // 데이터 상태 : stateFlow - 컴포저블 UI - collect state
    private val _state = MutableStateFlow(OneLineLogState())
    val state = _state.asStateFlow()

    // Intent

    // SideEffect :
    private val _sideEffect = Channel<OneLineLogSideEffect?>()
    val sideEffect = _sideEffect.receiveAsFlow()



    fun onIntent(intent: OneLineLogIntent) {
        when(intent) {
            is OneLineLogIntent.OnSave -> { // 한줄로그가 저장될 때
                save()
            }
            is OneLineLogIntent.OnChanged -> {  // 한줄로그가 변경될 때

            }
            is OneLineLogIntent.OnTextChanged -> {  // 입력 텍스트가 변경되었을 때
                _state.update {
                    it.copy(writingText = intent.text)
                }
            }
        }
    }

    private fun save() {
        Log.d("TAG", "save: ")
        viewModelScope.launch {
//            _sideEffect.send(OneLineLogSideEffect.ShowToast("저장되었습니다.1"))
            _sideEffect.send(OneLineLogSideEffect.ShowSavedToast)

        }

    }
}