package com.xunobulax.rambutan.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "members",
    foreignKeys = [
        ForeignKey(
            entity = Group::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("group_id"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Member(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "first_name")
    var firstName: String = "",

    @ColumnInfo(name = "last_name")
    var lastName: String = "",

    var email: String = "",

    @ColumnInfo(name = "group_id")
    var groupId: Int
)