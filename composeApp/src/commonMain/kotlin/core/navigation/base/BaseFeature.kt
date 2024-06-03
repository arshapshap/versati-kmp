package core.navigation.base

import androidx.navigation.NamedNavArgument

open class BaseFeature(featureName: String, screenName: String) {

    open class Companion(val featureRoute: String)

    private val baseRoute = "$featureName/$screenName"

    open val route get() = baseRoute
    fun destination() = baseRoute

    protected fun route(arguments: List<NamedNavArgument>): String {
        return getArgumentsString(arguments.associate { it.name to "{${it.name}}" })
    }

    protected fun destination(arguments: Map<NamedNavArgument, Any>): String {
        return getArgumentsString(arguments.mapKeys { it.key.name }
            .mapValues { it.value.toString() })
    }

    private fun getArgumentsString(arguments: Map<String, String>): String {
        val result = StringBuilder(baseRoute)
        result.append("?")
        arguments.entries.forEachIndexed { i, it ->
            result.append("${it.key}=${it.value}")
            if (i != arguments.size - 1) result.append("&")
        }
        return result.toString()
    }
}

fun String.getFeatureRoute() = this.takeWhile { it != '/' }