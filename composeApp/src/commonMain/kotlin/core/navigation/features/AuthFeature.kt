package core.navigation.features

import core.navigation.base.BaseFeature

sealed class AuthFeature(screenName: String) : BaseFeature(featureRoute, screenName) {

    companion object : BaseFeature.Companion("auth_feature")

    data object Register : AuthFeature("register")

    data object SignIn : AuthFeature("sign_in")
}