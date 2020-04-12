package com.xunobulax.rambutan.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.xunobulax.rambutan.utilities.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate


class PersonDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var personDao: PersonDao

    private val personJohn = Person(
        1,
        "John",
        "Doe",
        "johndoe@email.com",
        LocalDate.parse("2000-12-25"),
        2
    )
    private val personJane = Person(
        2,
        "Jane",
        "Doe",
        "janedoe@email.com",
        LocalDate.of(2020, 1, 1),
        1
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        personDao = database.personDao()
        personDao.insertPerson(personJohn)
        personDao.insertPerson(personJane)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetPeople() {
        val people = personDao.getPeople().getOrAwaitValue()
        assertThat(people.size, equalTo(2))

        assertThat(people[0], equalTo(personJane))
        assertThat(people[1], equalTo(personJohn))
    }

}