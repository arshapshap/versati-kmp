package feature.auth.data.helper

import com.russhwolf.settings.Settings

private const val CURRENT_USER_KEY = "CURRENT_USER_KEY"
class AuthSettingsHelper(
    private val settings: Settings
) {

    fun logIn(userId: Long) {
        settings.putLong(CURRENT_USER_KEY, userId)
    }

    fun logOut() {
        settings.remove(CURRENT_USER_KEY)
    }

    fun getCurrentUserId(): Long? = settings.getLongOrNull(CURRENT_USER_KEY)
}