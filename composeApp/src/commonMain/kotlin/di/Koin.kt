package di

import core.database.di.databaseDaoModule
import feature.auth.di.authFeatureModule
import feature.charts.di.chartsFeatureModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object Koin {

    fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
        startKoin {
            appDeclaration()
            modules(
                platformModule,
                databaseDaoModule,
                authFeatureModule,
                chartsFeatureModule
            )
        }
}