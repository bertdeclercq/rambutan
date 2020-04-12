package com.xunobulax.rambutan.data

import com.xunobulax.rambutan.data.Family
import com.xunobulax.rambutan.data.Person
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class FamilyTest {

    private lateinit var family: Family
    private lateinit var person: Person

    @Before
    fun setUp() {
        family = Family()

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
    fun testFamilyHasMember() {
        family.addMember(person)
        assertEquals("John", family.members[0].firstName)
    }
}