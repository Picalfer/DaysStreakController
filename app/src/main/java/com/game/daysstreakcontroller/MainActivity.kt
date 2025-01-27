package com.game.daysstreakcontroller

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.game.daysstreakcontroller.ui.theme.DaysStreakControllerTheme

class MainActivity : ComponentActivity() {

    private val serializer = StreakJSONSerializer(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            DaysStreakControllerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DaysStreak(
                        serializer.loadStreak(),
                        modifier = Modifier.padding(innerPadding),
                        onAdmitDayClick = { newStreak ->
                            serializer.saveStreak(newStreak)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DaysStreak(
    streak: Int,
    modifier: Modifier = Modifier,
    onAdmitDayClick: (Int) -> Unit,
) {
    var currentStreak by remember { mutableStateOf(streak) }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val lastAdmitDay = sharedPreferences.getLong("lastAdmitDay", 0L)

    // Получаем текущее время
    val currentDay = System.currentTimeMillis() / (1000 * 60 * 60 * 24)

    // Проверяем, можно ли нажимать кнопку
    val isButtonEnabled = lastAdmitDay != currentDay

    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(Alignment.Center)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(currentStreak.toString())
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        currentStreak += 1
                        onAdmitDayClick(currentStreak)

                        // Сохраняем текущий день в SharedPreferences
                        sharedPreferences.edit().putLong("lastAdmitDay", currentDay).apply()
                    },
                    enabled = isButtonEnabled // Деактивируем кнопку, если день не изменился
                ) {
                    Text("Отметить день")
                }
            }
        }
    }
}
