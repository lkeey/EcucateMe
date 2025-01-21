package dev.lkeeeey.edu

import android.app.Application
import dev.lkeeeey.edu.di.initKoin
import org.koin.android.ext.koin.androidContext

class EducateMeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@EducateMeApp)
        }
    }
}
