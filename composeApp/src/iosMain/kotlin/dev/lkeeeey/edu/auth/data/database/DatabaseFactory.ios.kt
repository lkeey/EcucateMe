//@file:OptIn(ExperimentalForeignApi::class)
//
//package dev.lkeeeey.edu.auth.data.database
//
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import kotlinx.cinterop.ExperimentalForeignApi
//import platform.Foundation.NSDocumentDirectory
//import platform.Foundation.NSFileManager
//import platform.Foundation.NSUserDomainMask
//
//actual class DatabaseFactory {
//    actual fun create(): RoomDatabase.Builder<UserDatabase> {
//        val dbFile = documentDirectory() + "/${UserDatabase.DB_NAME}"
//        return Room.databaseBuilder<UserDatabase>(
//            name = dbFile
//        )
//    }
//
//    private fun documentDirectory() : String {
//         val docDirectory = NSFileManager.defaultManager.URLForDirectory(
//             directory = NSDocumentDirectory,
//             inDomain = NSUserDomainMask,
//             appropriateForURL = null,
//             create = false,
//             error = null
//         )
//
//        return requireNotNull(docDirectory?.path)
//    }
//}