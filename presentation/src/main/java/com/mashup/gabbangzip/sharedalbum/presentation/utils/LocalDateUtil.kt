package com.mashup.gabbangzip.sharedalbum.presentation.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateUtil {
    fun getNowDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yy/MM/dd")
        return LocalDate.now().format(formatter)
    }
}
