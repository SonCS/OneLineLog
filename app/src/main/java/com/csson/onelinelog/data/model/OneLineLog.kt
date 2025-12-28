package com.csson.onelinelog.data.model

import org.json.JSONArray
import org.json.JSONObject

data class OneLineLog (
    val content: String,
    val createdAt: String
) {
    companion object {
        // String to OneLineLog decode
        fun decode(json: String): List<OneLineLog> = JSONArray(json).let { array ->
            return List(array.length()) { index ->
                val item = array.getJSONObject(index)
                return@List OneLineLog(
                    content = item.getString("content"),
                    createdAt = item.getString("createdAt")
                )
            }
        }
        // List<OneLineLog> to String encode
        fun encode(oneLineLogs: List<OneLineLog>): String  {
            return JSONArray().apply {
                oneLineLogs.forEach {
                    put(
                        JSONObject().apply {
                        put("content", it.content)
                        put("createdAt", it.createdAt)
                    })
                }
            }.toString()
        }
    }
}




