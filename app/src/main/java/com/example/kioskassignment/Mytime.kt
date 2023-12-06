package com.example.kioskassignment

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * 시간 관련 기능 오브젝트
 */
object Mytime {
    /** "yyyy.MM.dd. HH:mm:ss" formatter */
    var formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss")

    /** "HH시 mm분" formatter */
    var formatterHHmm = DateTimeFormatter.ofPattern("HH시 mm분")

    /** LocalTime.now() */
    fun nowTime(): LocalTime = LocalTime.now()

    /** 현재 시간을 "yyyy.MM.dd. HH:mm:ss" 형태로 반환 */
    fun nowDateTimeFormatted(): String = LocalDateTime.now().format(formatter)

    /** 시간값을 "HH시 mm분" 형태로 반환 */
    fun timeHHmm(time: LocalTime): String = time.format(formatterHHmm)

    /** "HH:mm:ss" String을 입력받아 시간값(LocalTime)으로 반환 */
    fun parseTime(text: String): LocalTime = LocalTime.parse(text)
}
