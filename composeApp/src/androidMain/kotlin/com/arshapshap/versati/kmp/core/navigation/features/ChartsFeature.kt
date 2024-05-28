package com.arshapshap.versati.kmp.core.navigation.features

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.arshapshap.versati.kmp.core.navigation.base.BaseFeature

sealed class ChartsFeature(screenName: String) : BaseFeature(featureRoute, screenName) {

    companion object : BaseFeature.Companion("charts_feature")

    data object ChartGeneration : ChartsFeature("chart_generation") {

        const val idArgument = "id"
        val arguments = listOf(
            navArgument(idArgument) {
                type = NavType.LongType
                defaultValue = 0L
            }
        )

        fun destination(id: Long = 0L) = destination(mapOf(arguments[0] to id))
        override val route get() = route(arguments)
    }

    data object ChartsHistory : ChartsFeature("history")
}