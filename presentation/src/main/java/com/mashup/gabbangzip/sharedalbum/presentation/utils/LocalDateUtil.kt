package com.mashup.gabbangzip.sharedalbum.presentation.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object LocalDateUtil {
    fun getNowDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yy/MM/dd")
        return LocalDate.now().format(formatter)
    }

    fun format(date: LocalDateTime, pattern: String = "yyyy.MM.dd"): String {
        return date
            .atZone(ZoneId.of("Asia/Seoul"))
            .format(DateTimeFormatter.ofPattern(pattern, Locale.KOREAN))
    }
}
