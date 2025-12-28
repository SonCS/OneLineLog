package com.csson.onelinelog.mvi

import com.csson.onelinelog.data.model.OneLineLog

// 현재 한줄 일기(데이터) 상태
// - 일기 목록 : List<데이터 모델>
// - 작성 중인 글자 : String

data class OneLineLogState(
    val writingText: String = "",
    val oneLineLogs: List<OneLineLog> = emptyList()
)