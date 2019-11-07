package com.xunobulax.rambutan.database

import androidx.room.*
import com.xunobulax.rambutan.model.Group


@Dao
interface GroupDao {

    @Insert
    fun insertGroup(group: Group)

    @Update
    fun updateGroup(group: Group)

    @Delete
    fun deleteGroup(group: Group)

    @Query("SELECT * FROM groups WHERE id = :key")
    fun get(key: Int): Group?

    @Query("SELECT * FROM groups")
    fun getAllGroups(): List<Group>
}