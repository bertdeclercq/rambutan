package com.xunobulax.rambutan

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.xunobulax.rambutan.data.AppDatabase
import com.xunobulax.rambutan.data.Person
import com.xunobulax.rambutan.data.PersonDao
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.time.LocalDate


class DatabaseTest {

    private lateinit var personDao: PersonDao
    private lateinit var db: AppDatabase

    private val john = Person(
        1,
        "John",
        "Doe",
        "johndoe@email.com",
        LocalDate.parse("2000-12-25"),
        2
    )

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        personDao = db.personDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertAndGetPerson() {
        personDao.insertPerson(john)
        val personFromDb = personDao.getPerson(1)
        assertEquals(john, personFromDb)
    }

}