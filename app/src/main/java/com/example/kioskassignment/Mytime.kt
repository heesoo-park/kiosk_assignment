package com.example.kioskassignment

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Mytime {
    var formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss")
    var formatterHHmm = DateTimeFormatter.ofPattern("HH시 mm분")

    fun nowDateTimeFormatted(): String = LocalDateTime.now().format(formatter)
    fun nowTime(): LocalTime = LocalTime.now()
    fun timeHHmm(time: LocalTime): String = time.format(formatterHHmm)

    fun parseTime(text: String): LocalTime = LocalTime.parse(text)
}
