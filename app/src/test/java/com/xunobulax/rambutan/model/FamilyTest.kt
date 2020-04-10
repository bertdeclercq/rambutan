package com.xunobulax.rambutan.model

import com.xunobulax.rambutan.data.Family
import com.xunobulax.rambutan.data.Person
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class FamilyTest {

    private val family = Family()

    private val john = Person(
        1,
        "John",
        "Doe",
        "johndoe@email.com",
        LocalDate.parse("2000-12-25"),
        2
    )

    @Test
    fun testFamilyHasMember() {
        family.addMember(john)
        assertEquals("John", family.members.elementAt(0).firstName)
    }
}