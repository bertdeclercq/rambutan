package com.xunobulax.rambutan.utilities

import androidx.room.TypeConverter
import java.time.LocalDate


class Converters {
    @TypeConverter
    fun dateToDatestamp(date: LocalDate?): Long? = date?.toEpochDay()

    @TypeConverter
    fun datestampToDate(value: Long?): LocalDate? = LocalDate.ofEpochDay(value ?: 0)
}