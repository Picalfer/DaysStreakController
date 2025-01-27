package com.game.daysstreakcontroller

import android.content.Context
import android.util.Log
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Writer

class StreakJSONSerializer(private val context: Context) {
    fun saveStreak(streak: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("STREAK", streak)
        Log.d("WorkOutAppJSON", jsonObject.toString())
        var writer: Writer? =
            null // Инициализируем переменную writer как null, чтобы использовать ее позже для записи в файл
        try {
            // Открываем файл для записи в режиме PRIVATE, что означает, что файл будет доступен только этому приложению
            val out = context.openFileOutput("streak.json", Context.MODE_PRIVATE)

            // Создаем OutputStreamWriter, который будет использоваться для записи в выходной поток
            writer = OutputStreamWriter(out)

            // Записываем строковое представление JSON-массива в файл
            writer.write(jsonObject.toString())
        } finally {
            // Закрываем writer, если он не равен null, чтобы освободить ресурсы
            writer?.close()
        }
    }

    fun loadStreak(): Int {
        var reader: BufferedReader? = null
        var streak = 0
        try {
            // Открытие и чтение файла в StringBuilder
            val inputStream = context.openFileInput("streak.json")
            reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = StringBuilder()
            var line: String?

            // Чтение файла построчно
            while (reader.readLine().also { line = it } != null) {
                jsonString.append(line)
            }

            // Разбор JSON с использованием JSONTokener
            val jsonObject = JSONTokener(jsonString.toString()).nextValue() as JSONObject
            Log.d("TEST", jsonObject.toString())
            streak = jsonObject.get("STREAK") as Int
        } catch (e: FileNotFoundException) {
            // Происходит при начале "с нуля"; не обращайте внимания
        } finally {
            // Закрываем reader, если он не равен null
            reader?.close()
        }
        return streak
    }
}