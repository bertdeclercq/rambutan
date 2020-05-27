package com.xunobulax.rambutan.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {

    // queries returning LiveData are ran asynchronously

    @Query("SELECT * FROM people ORDER BY first_name")
    fun getPeople(): LiveData<List<Person>>

    @Query("SELECT * FROM people ORDER BY birthday ASC")
    fun getPeopleByBirthdayAsc(): LiveData<List<Person>>

    @Query("SELECT * FROM people ORDER BY birthday DESC")
    fun getPeopleByBirthdayDesc(): LiveData<List<Person>>

    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getPerson(id: Long): Person

    // suspend makes the methods asynchronous using Kotlin coroutines

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person): Long?

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

}