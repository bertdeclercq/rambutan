package com.xunobulax.rambutan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xunobulax.rambutan.model.Group
import com.xunobulax.rambutan.model.Member


const val NAME = "member_database"


@Database(
    entities = [
        Group::class,
        Member::class],
    version = 1,
    exportSchema = false
)
abstract class MemberDatabase : RoomDatabase() {

    abstract val groupDao: GroupDao
    abstract val memberDao: MemberDao

    companion object {

        @Volatile
        private var INSTANCE: MemberDatabase? = null

        fun getInstance(context: Context): MemberDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemberDatabase::class.java,
                        NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance

                return instance
            }
        }
    }
}