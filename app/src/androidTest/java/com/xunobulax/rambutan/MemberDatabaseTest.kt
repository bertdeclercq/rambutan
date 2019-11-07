package com.xunobulax.rambutan

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.xunobulax.rambutan.database.GroupDao
import com.xunobulax.rambutan.database.MemberDao
import com.xunobulax.rambutan.database.MemberDatabase
import com.xunobulax.rambutan.model.Group
import com.xunobulax.rambutan.model.Member
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException


class MemberDatabaseTest {

    private lateinit var groupDao: GroupDao
    private lateinit var memberDao: MemberDao
    private lateinit var db: MemberDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, MemberDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        groupDao = db.groupDao
        memberDao = db.memberDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertAndGetGroup() {
        val group = Group(1, "Doe family")
        groupDao.insertGroup(group)
        assertEquals("Doe family", groupDao.get(1)?.name)
    }

    @Test
    @Throws(IOException::class)
    fun insertAndGetMember() {
        val group = Group(1, "Doe family")
        groupDao.insertGroup(group)
        val member = Member(1, "John", "Doe", "johndoe@email.com", 1)
        memberDao.insertMember(member)
        assertEquals("johndoe@email.com", memberDao.get(1)?.email)
    }

    @Test
    @Throws(IOException::class)
    fun deletingGroupDeletesMembers() {
        val group = Group(1, "Doe family")
        groupDao.insertGroup(group)
        val members = arrayListOf<Member>(
            Member(1, "John", "Doe", "johndoe@email.com", 1),
            Member(2, "Jane", "Doe", "janedoe@email.com", 1)
        )
        for (member in members) {
            memberDao.insertMember(member)
        }
        groupDao.deleteGroup(group)
        assertTrue(memberDao.getMembersFromGroup(1).isEmpty())
    }
}