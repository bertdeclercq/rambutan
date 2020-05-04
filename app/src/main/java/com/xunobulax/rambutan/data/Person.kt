package com.xunobulax.rambutan.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "people")
data class Person @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName: String? = "",

    @ColumnInfo(name = "last_name")
    var lastName: String? = "",

    var email: String? = "",

    var birthday: LocalDate? = null,

    @ColumnInfo(name = "partner_id")
    var partnerId: Int? = null
)