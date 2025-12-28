package com.csson.onelinelog.mvi


// 데이터를 변경시키지 않는 액션
sealed interface OneLineLogSideEffect {
    object ShowSavedToast : OneLineLogSideEffect
    data class ShowToast(val message: String) : OneLineLogSideEffect
}
