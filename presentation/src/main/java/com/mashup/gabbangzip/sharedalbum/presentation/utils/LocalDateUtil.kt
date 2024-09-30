package com.mashup.gabbangzip.sharedalbum.presentation.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object LocalDateUtil {
    fun format(date: LocalDateTime, pattern: String = "yyyy.MM.dd"): String {
        return date
            .atZone(ZoneId.of("Asia/Seoul"))
            .format(DateTimeFormatter.ofPattern(pattern, Locale.KOREAN))
    }

    fun parseLongToLocalDateTime(date: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.of("Asia/Seoul"))
    }
}
