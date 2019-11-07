package com.xunobulax.rambutan.database

import androidx.room.*
import com.xunobulax.rambutan.model.Member


@Dao
interface MemberDao {

    @Insert
    fun insertMember(member: Member)

    @Update
    fun updateMember(member: Member)

    @Delete
    fun deleteMember(member: Member)

    @Query("SELECT * FROM members WHERE id = :key")
    fun get(key: Int): Member?

    @Query("SELECT * FROM members WHERE group_id = :groupId")
    fun getMembersFromGroup(groupId: Int): List<Member>
}