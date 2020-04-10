package com.xunobulax.rambutan.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {

    // queries returning LiveData are ran asynchronously

    @Query("SELECT * FROM people ORDER BY first_name")
    fun getPeople(): List<Person>

    @Query("SELECT * FROM people ORDER BY birthday ASC")
    fun getPeopleByBirthdayAsc(): List<Person>

    @Query("SELECT * FROM people ORDER BY birthday DESC")
    fun getPeopleByBirthdayDesc(): List<Person>

    @Query("SELECT * FROM people WHERE id = :id")
    fun getPerson(id: Int): Person

    // suspend makes the methods asynchronous using Kotlin coroutines

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: Person)

    @Update
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

}