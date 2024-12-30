package dev.lkeeeey.edu.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.lkeeeey.edu.auth.data.database.DatabaseFactory
import dev.lkeeeey.edu.auth.data.database.UserDatabase
import dev.lkeeeey.edu.auth.data.network.KtorRemoteBookDataSource
import dev.lkeeeey.edu.auth.data.network.RemoteAuthDataSource
import dev.lkeeeey.edu.auth.data.repository.AuthRepositoryImpl
import dev.lkeeeey.edu.auth.domain.AuthRepository
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterViewModel
import dev.lkeeeey.edu.auth.presentation.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.core.data.HttpClientFactory
import dev.lkeeeey.edu.library.data.network.RemoteLibraryRepository
import dev.lkeeeey.edu.library.data.network.RemoteLibraryRepositoryImpl
import dev.lkeeeey.edu.library.data.repository.LibraryRepositoryImpl
import dev.lkeeeey.edu.library.domain.LibraryRepository
import dev.lkeeeey.edu.main.data.network.RemoteProfileDataSource
import dev.lkeeeey.edu.main.data.network.RemoteProfileDataSourceImpl
import dev.lkeeeey.edu.main.data.repository.ProfileRepositoryImpl
import dev.lkeeeey.edu.main.domain.ProfileRepository
import dev.lkeeeey.edu.main.presentation.calendar.viewmodel.CalendarViewModel
import dev.lkeeeey.edu.main.presentation.profile.subjects.viewmodel.SubjectsViewModel
import dev.lkeeeey.edu.main.presentation.profile.timetable.viewmodel.TimeTableViewModel
import dev.lkeeeey.edu.library.presentation.teachers.viewmodel.AllTeachersViewModel
import dev.lkeeeey.edu.profile.presentation.viewmodel.ProfileViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single { HttpClientFactory.create(get()) }

    singleOf(::KtorRemoteBookDataSource).bind<RemoteAuthDataSource>()
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()

    singleOf(::RemoteProfileDataSourceImpl).bind<RemoteProfileDataSource>()
    singleOf(::ProfileRepositoryImpl).bind<ProfileRepository>()

    singleOf(::RemoteLibraryRepositoryImpl).bind<RemoteLibraryRepository>()
    singleOf(::LibraryRepositoryImpl).bind<LibraryRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<UserDatabase>().userDao }


    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::CalendarViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::TimeTableViewModel)
    viewModelOf(::SubjectsViewModel)
    viewModelOf(::AllTeachersViewModel)

}
