package com.xunobulax.rambutan.data

import androidx.room.*
import java.time.LocalDate


@Entity(
    tableName = "people",
//    foreignKeys = [ForeignKey(
//        entity = Person::class,
//        parentColumns = ["id"],
//        childColumns = ["partner_id"],
//        onDelete = ForeignKey.SET_NULL
//    )],
    indices = [Index(value = ["email"], unique = true),
        Index(value = ["first_name", "last_name"], unique = true)]
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