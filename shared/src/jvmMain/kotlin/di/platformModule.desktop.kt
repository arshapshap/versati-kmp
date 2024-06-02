package di

import core.database.AppDatabase
import core.database.PlatformDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<AppDatabase> { PlatformDatabase.getDatabase() }
    }