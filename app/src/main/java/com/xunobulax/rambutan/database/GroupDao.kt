package com.xunobulax.rambutan.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.xunobulax.rambutan.model.Group


@Dao
interface GroupDao {

    @Insert
    fun insertGroup(group: Group)

    @Update
    fun updateGroup(group: Group)

    @Query("SELECT * FROM groups WHERE id = :key")
    fun get(key: Int): Group?

    @Query("SELECT * FROM groups")
    fun getAllGroups(): List<Group>
}