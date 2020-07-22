package com.xunobulax.rambutan.data

import androidx.room.*


@Entity(
    tableName = "pair_rules",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["person_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["partner_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
class PairRule @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "person_id")
    var personId: Long = 0L,

    @ColumnInfo(name = "partner_id")
    val partnerId: Long,

    @ColumnInfo(name = "pairable")
    var pairable: Boolean
)


@Dao
interface PairRuleDao {

    @Query("SELECT * FROM pair_rules")
    suspend fun getPairRules(): List<PairRule>

    @Query("SELECT partner_id FROM pair_rules WHERE person_id = :personId AND NOT pairable")
    suspend fun getPartnerId(personId: Long): Long?

    @Query("SELECT * FROM pair_rules WHERE person_id = :id OR partner_id = :id")
    suspend fun getPairRulesFor(id: Long): List<PairRule>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPairRule(rule: PairRule)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPairRules(vararg rule: PairRule)

    @Delete
    suspend fun deletePairRule(rule: PairRule)

    @Delete
    suspend fun deletePairRules(rules: List<PairRule>)
}