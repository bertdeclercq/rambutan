package com.xunobulax.rambutan.model

import org.junit.Assert.assertEquals
import org.junit.Test

class GroupTest {

    @Test
    fun testGroupNameEquals() {
        val group = Group(1, "Family")
        assertEquals("Family", group.name)
    }
}