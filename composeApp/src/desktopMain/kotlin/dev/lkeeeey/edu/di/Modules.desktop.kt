package dev.lkeeeey.edu.di

import dev.lkeeeey.edu.auth.data.database.DatabaseFactory
import dev.lkeeeey.edu.datastore.createDataStore
import dev.lkeeeey.edu.datastore.dataStoreFileName
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single { DatabaseFactory() }
        single { createDataStore { dataStoreFileName } }

    }