package com.xunobulax.rambutan.model

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(tableName = "group_member_join",
    primaryKeys = ["groupId", "memberId"],
    foreignKeys = [ForeignKey(entity = Group::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("groupId")), ForeignKey(entity = Member::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("memberId"))]
)
data class GroupMemberJoin(
    val groupId: Int,
    val memberId: Int
)