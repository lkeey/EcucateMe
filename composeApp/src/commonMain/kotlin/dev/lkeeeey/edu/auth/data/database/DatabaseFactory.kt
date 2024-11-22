package dev.lkeeeey.edu.auth.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<UserDatabase>
}
