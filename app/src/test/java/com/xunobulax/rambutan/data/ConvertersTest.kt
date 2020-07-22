package com.xunobulax.rambutan.data

import com.xunobulax.rambutan.utilities.Converters
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

class ConvertersTest {

    private val date = LocalDate.parse("2020-01-25")

    @Test
    fun dateToDatestamp() {
        assertEquals(date.toEpochDay(), Converters()
            .dateToDatestamp(date))
    }

    @Test
    fun datestampToDate() {
        assertEquals(date, Converters().datestampToDate(date.toEpochDay()))
    }
}