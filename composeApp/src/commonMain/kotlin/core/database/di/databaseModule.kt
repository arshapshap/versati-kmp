package core.database.di

import core.database.AppDatabase
import core.database.dao.authfeature.UserDao
import core.database.dao.chartsfeature.ChartDao
import core.database.dao.imageparsingfeature.ParsingResultDao
import core.database.dao.qrcodesfeature.QRCodeRequestDao
import org.koin.dsl.module

val databaseDaoModule = module {
    single<UserDao> { get<AppDatabase>().userDao() }
    single<ChartDao> { get<AppDatabase>().chartDao() }
    single<ParsingResultDao> { get<AppDatabase>().parsingResultDao() }
    single<QRCodeRequestDao> { get<AppDatabase>().qrCodeRequestDao() }
}