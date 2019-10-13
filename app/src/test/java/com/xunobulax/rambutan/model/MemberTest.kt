package com.xunobulax.rambutan.model

import org.junit.Assert.assertEquals
import org.junit.Test

class MemberTest {

    @Test
    fun testFirstNameEquals() {
        val member = Member(1, "John", "Doe", "johndoe@email.com")
        assertEquals("John", member.firstName)
    }

    @Test
    fun testLastNameEquals() {
        val member = Member(1, "John", "Doe", "johndoe@email.com")
        assertEquals("Doe", member.lastName)
    }

    @Test
    fun testEmailEquals() {
        val member = Member(1, "John", "Doe", "johndoe@email.com")
        assertEquals("johndoe@email.com", member.email)
    }
}