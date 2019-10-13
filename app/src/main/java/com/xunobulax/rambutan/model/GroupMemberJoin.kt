package com.xunobulax.rambutan.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    tableName = "group_member_join",
    primaryKeys = ["group_id", "member_id"],
    foreignKeys = [
        ForeignKey(
            entity = Group::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("group_id")
        ),
        ForeignKey(
            entity = Member::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("member_id")
        )]
)
data class GroupMemberJoin(
    @ColumnInfo(name = "group_id")
    val groupId: Int,

    @ColumnInfo(name = "member_id")
    val memberId: Int
)