package com.xunobulax.rambutan.model

import com.xunobulax.rambutan.data.Person
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class PersonTest {

    private val john = Person(
        1,
        "John",
        "Doe",
        "johndoe@email.com",
        LocalDate.parse("2000-12-25"),
        2
    )

    @Test
    fun testFirstNameEquals() {
        assertEquals("John", john.firstName)
    }

    @Test
    fun testLastNameEquals() {
        assertEquals("Doe", john.lastName)
    }

    @Test
    fun testEmailEquals() {
        assertEquals("johndoe@email.com", john.email)
    }

    @Test
    fun testBirthdayEquals() {
        assertEquals("2000-12-25", john.birthday.toString())
    }

    @Test
    fun testPartnerIdEquals() {
        assertEquals(2, john.partnerId)
    }
}