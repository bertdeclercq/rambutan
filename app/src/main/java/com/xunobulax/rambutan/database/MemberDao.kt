package com.xunobulax.rambutan.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.xunobulax.rambutan.model.Member


@Dao
interface MemberDao {

    @Insert
    fun insertMember(member: Member)

    @Update
    fun updateMember(member: Member)

    @Query("SELECT * FROM members WHERE id = :key")
    fun get(key: Int): Member?

    @Query("SELECT * FROM members WHERE group_id = :groupId")
    fun getMembersFromGroup(groupId: Int): List<Member>
}