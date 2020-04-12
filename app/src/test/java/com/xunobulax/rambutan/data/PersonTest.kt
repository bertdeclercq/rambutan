package com.xunobulax.rambutan.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class PersonTest {

    private lateinit var person: Person

    @Before
    fun setUp() {
        person = Person(
            1,
            "John",
            "Doe",
            "johndoe@email.com",
            LocalDate.parse("2000-12-25"),
            2
        )
    }

    @Test
    fun testFirstName() {
        assertEquals("John", person.firstName)
    }

    @Test
    fun testLastName() {
        assertEquals("Doe", person.lastName)
    }

    @Test
    fun testEmail() {
        assertEquals("johndoe@email.com", person.email)
    }

    @Test
    fun testBirthday() {
        assertEquals("2000-12-25", person.birthday.toString())
    }

    @Test
    fun testPartnerId() {
        assertEquals(2, person.partnerId)
    }
}