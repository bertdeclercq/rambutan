package com.xunobulax.rambutan.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.time.LocalDate


@Entity(
    tableName = "people",
    indices = [Index(value = ["email"], unique = true),
        Index(value = ["first_name", "last_name"], unique = true)]
)
// Use @JvmOverloads when there are default values
data class Person @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "first_name")
    var firstName: String? = "",

    @ColumnInfo(name = "last_name")
    var lastName: String? = "",

    var email: String? = "",

    var birthday: LocalDate? = null
)


@Dao
interface PersonDao {

    // queries returning LiveData are ran asynchronously

    @Query("SELECT * FROM people ORDER BY first_name")
    fun getPeople(): LiveData<List<Person>>

    @Query("SELECT * FROM people ORDER BY birthday ASC")
    fun getPeopleByBirthdayAsc(): LiveData<List<Person>>

    @Query("SELECT * FROM people ORDER BY birthday DESC")
    fun getPeopleByBirthdayDesc(): LiveData<List<Person>>

    @Query("SELECT * FROM people WHERE NOT id = :personId ORDER BY first_name")
    fun getPotentialPartnersFor(personId: Long): LiveData<List<Person>>

    // suspend makes the methods asynchronous using Kotlin coroutines

    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getPerson(id: Long): Person

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person): Long?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

}