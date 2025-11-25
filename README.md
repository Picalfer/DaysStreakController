# DaysStreakController

[![Kotlin](https://img.shields.io/badge/Kotlin-1.8+-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg?logo=android)](https://www.android.com/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack-Compose-blue.svg?logo=android)](https://developer.android.com/jetpack/compose)

Простое приложение для отслеживания ежедневных стрик-цепочек с защитой от многократных отметок в один день.

## О проекте

Приложение позволяет пользователям отмечать выполнение ежедневных привычек и отслеживать текущую серию дней. Включает защиту от накрутки - только одна отметка в день.

**Основной функционал:**
- Отслеживание текущей серии дней
- Кнопка для отметки выполнения дня
- Защита от повторных отметок в течение дня
- Сохранение данных между запусками приложения
- Простой и интуитивный интерфейс

## Технологии

* Kotlin
* Jetpack Compose
* JSON сериализация для сохранения данных
* SharedPreferences для контроля временных меток
* MVVM архитектура
