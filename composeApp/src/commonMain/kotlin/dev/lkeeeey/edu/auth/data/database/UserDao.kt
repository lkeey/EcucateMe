package dev.lkeeeey.edu.auth.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT * FROM BookEntity")
    fun getUser() : Flow<List<UserEntity>>

}
