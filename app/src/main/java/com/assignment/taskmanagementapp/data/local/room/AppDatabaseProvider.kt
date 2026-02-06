package com.assignment.taskmanagementapp.data.local.room

import android.content.Context
import androidx.room.Room

object AppDatabaseProvider {
    @Suppress("ktlint:standard:property-naming")
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
        }

    private fun buildDatabase(context: Context): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, "task.db")
            .fallbackToDestructiveMigration(false)
            .build()

    fun close() {
        INSTANCE?.close()
        INSTANCE = null
    }
}
