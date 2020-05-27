package com.xunobulax.rambutan.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(
    tableName = "people",
    foreignKeys = [ForeignKey(
        entity = Person::class,
        parentColumns = ["id"],
        childColumns = ["partner_id"],
        onDelete = ForeignKey.SET_NULL
    )]
)
data class Person @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "first_name")
    var firstName: String? = "",

    @ColumnInfo(name = "last_name")
    var lastName: String? = "",

    var email: String? = "",

    var birthday: LocalDate? = null,

    @ColumnInfo(name = "partner_id")
    var partnerId: Long? = null
) {

    fun hasPartner(): Boolean = (partnerId != null && partnerId!! > 0)

}