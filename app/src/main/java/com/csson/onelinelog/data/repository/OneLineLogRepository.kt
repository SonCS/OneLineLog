package com.csson.onelinelog.data.repository

import com.csson.onelinelog.data.datastore.OneLineLogDataStore
import com.csson.onelinelog.data.model.OneLineLog
import kotlinx.coroutines.flow.first
import java.time.LocalDate

class OneLineLogRepository (
    private val dataStore: OneLineLogDataStore
) {
    val oneLineLogs = dataStore.oneLineLogsFlow()

    suspend fun saveOneLineLog(content: String, date: String = LocalDate.now().toString()) {
        val existingOneLineLogs = dataStore.oneLineLogsFlow().first()
        val updatedOneLineLogs = existingOneLineLogs + OneLineLog(
            content,
            date
        )
        dataStore.save(updatedOneLineLogs)
    }
}