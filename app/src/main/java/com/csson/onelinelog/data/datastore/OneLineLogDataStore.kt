package com.csson.onelinelog.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.csson.onelinelog.data.model.OneLineLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.OneLineLogsDataStore: DataStore<Preferences> by preferencesDataStore(name = "one_line_logs")

class OneLineLogDataStore(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val ONE_LINE_LOGS = stringPreferencesKey("one_line_logs")
    }


    // 저장된 데이터를 가져오는 Flow
    fun oneLineLogsFlow(): Flow<List<OneLineLog>> = dataStore.data.map { preferences ->
        val storedJson = preferences[ONE_LINE_LOGS] ?:return@map emptyList<OneLineLog>()
        return@map OneLineLog.decode(storedJson)
    }


    suspend fun save(updatedOneLineLogs: List<OneLineLog>) {
        dataStore.updateData {
            it.toMutablePreferences().also {preferences ->
                preferences[ONE_LINE_LOGS] = OneLineLog.encode(updatedOneLineLogs)
            }
        }
    }
}