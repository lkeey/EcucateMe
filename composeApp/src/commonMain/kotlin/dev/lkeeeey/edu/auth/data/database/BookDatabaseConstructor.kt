package dev.lkeeeey.edu.auth.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<UserDatabase> {
    override fun initialize(): UserDatabase
}