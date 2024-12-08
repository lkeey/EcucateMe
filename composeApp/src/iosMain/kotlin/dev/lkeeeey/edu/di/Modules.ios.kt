package dev.lkeeeey.edu.di

import dev.lkeeeey.edu.auth.data.database.DatabaseFactory
import dev.lkeeeey.edu.datastore.createDataStore
import dev.lkeeeey.edu.datastore.dataStoreFileName
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
        single { DatabaseFactory() }
        single { createDataStore {
            val directory = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )

            requireNotNull(directory).path + "/$dataStoreFileName"
        } }
    }