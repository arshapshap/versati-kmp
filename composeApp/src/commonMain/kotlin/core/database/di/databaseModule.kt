package core.database.di

import core.database.AppDatabase
import core.database.dao.UserDao
import org.koin.dsl.module

val databaseDaoModule = module {
    single<UserDao> { get<AppDatabase>().userDao() }
}