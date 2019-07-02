package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.extensions.TimeUnits.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

enum class TimeUnits(val size: Long) {
    SECOND(1000L),
    MINUTE(60 * SECOND.size),
    HOUR(60 * MINUTE.size),
    DAY(24 * HOUR.size)
}

val Int.sec get() = this * SECOND.size
val Int.min get() = this * MINUTE.size
val Int.hour get() = this * HOUR.size
val Int.day get() = this * DAY.size

val Long.asMin get() = this.absoluteValue / MINUTE.size
val Long.asHour get() = this.absoluteValue / HOUR.size
val Long.asDay get() = this.absoluteValue / DAY.size

private val minutesList: List<String> = listOf("минуту", "минуты", "минут")
private val hoursList: List<String> = listOf("час", "часа", "часов")
private val daysList: List<String> = listOf("день", "дня", "дней")

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = SECOND): Date {
    time += value * units.size
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - time

    return if (diff >= 0) {
        when (diff) {
            in 0.sec..1.sec -> "только что"
            in 2.sec..45.sec -> "несколько секунд назад"
            in 46.sec..75.sec -> "минуту назад"
            in 76.sec..45.min -> "${num2str(diff.asMin, minutesList)} назад"
            in 46.min..75.min -> "час назад"
            in 76.min..22.hour -> "${num2str(diff.asHour, hoursList)} назад"
            in 23.hour..26.hour -> "день назад"
            in 27.hour..360.day -> "${num2str(diff.asDay, daysList)} назад"
            else -> "более года назад"
        }
    } else {
        when (diff) {
            in (-1).sec..0.sec -> "прямо сейчас"
            in (-45).sec..(-1).sec -> "через несколько секунд"
            in (-75).sec..(-45).sec -> "через минуту"
            in (-45).min..(-75).sec -> "через ${num2str(diff.asMin, minutesList)}"
            in (-75).min..(-45).min -> "через час"
            in (-22).hour..(-75).min -> "через ${num2str(diff.asHour, hoursList)}"
            in (-26).hour..(-22).hour -> "через день"
            in (-360).day..(-26).hour -> "через ${num2str(diff.asDay, daysList)}"
            else -> "более чем через год"
        }
    }
}

fun num2str(number: Long, textForms: List<String>) = when {
    number % 100L in 5L..20L -> "$number ${textForms[2]}"
    number % 10L in 2L..4L -> "$number ${textForms[1]}"
    number % 10L == 1L -> "$number ${textForms[0]}"
    else -> "$number ${textForms[2]}"
}
