package com.xunobulax.rambutan.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "groups")
data class Group(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "group_name")
    var name: String = ""
)