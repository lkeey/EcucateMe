package dev.lkeeeey.edu.auth.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Update
    suspend fun updateUser(user: UserEntity)

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getUser() : Flow<List<UserEntity>>

    @Query("DELETE FROM UserEntity")
    fun deleteAllUsers()

}
