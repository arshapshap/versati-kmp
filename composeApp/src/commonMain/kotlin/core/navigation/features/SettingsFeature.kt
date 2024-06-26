package core.navigation.features

import core.navigation.base.BaseFeature

sealed class SettingsFeature(screenName: String) : BaseFeature(featureRoute, screenName) {

    companion object : BaseFeature.Companion("settings_feature")

    data object Settings : SettingsFeature("settings")
}