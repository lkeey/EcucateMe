package dev.lkeeeey.edu.auth.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey(autoGenerate = false) val id: String,
    val username: String,
    val refreshToken: String,
    val accessToken: String,
)
