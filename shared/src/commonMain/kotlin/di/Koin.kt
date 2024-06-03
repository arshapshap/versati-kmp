package di

import core.database.di.databaseModule
import core.network.networkModule
import feature.auth.di.authFeatureModule
import feature.charts.di.chartsFeatureModule
import feature.imageparsing.di.imageParsingFeatureModule
import feature.qrcodes.di.qrCodesFeatureModule
import feature.settings.di.settingsFeatureModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object Koin {

    fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
        startKoin {
            appDeclaration()
            modules(
                platformModule,
                databaseModule,
                networkModule,
                authFeatureModule,
                qrCodesFeatureModule,
                imageParsingFeatureModule,
                chartsFeatureModule,
                settingsFeatureModule
            )
        }
}