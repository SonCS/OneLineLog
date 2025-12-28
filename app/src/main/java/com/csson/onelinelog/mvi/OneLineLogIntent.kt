package com.csson.onelinelog.mvi

// OneLineLog State를 변경시키주는 부분.

// 상태를 변경시키주는 액션(인터럽션?)
sealed interface OneLineLogIntent {
    object OnSave: OneLineLogIntent
    object OnChanged: OneLineLogIntent

    data class OnTextChanged(val text: String): OneLineLogIntent
}