package com.myapp.buckwheat.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "buckwheat_prefs")

@Singleton
class AppPreferences @Inject constructor(
    private val context: Context
) {
    companion object {
        val PERIOD_START = stringPreferencesKey("period_start_date")
        val PERIOD_END = stringPreferencesKey("period_end_date")
        val CURRENCY_CODE = stringPreferencesKey("currency_code")
        val CURRENCY_SYMBOL = stringPreferencesKey("currency_symbol")
        val CURRENCY_CUSTOM = stringPreferencesKey("currency_custom")
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val RESIDUE_METHOD = stringPreferencesKey("residue_method")
        val REMEMBER_RESIDUE = booleanPreferencesKey("remember_residue")
        val HIDE_OVERSPEND = booleanPreferencesKey("hide_overspend")
        val ONBOARDING_DONE = booleanPreferencesKey("onboarding_done")
        val LAST_KNOWN_DATE = stringPreferencesKey("last_known_date")
    }

    private val dataStore = context.dataStore

    // Period
    val periodStart: Flow<String> = dataStore.data.map { it[PERIOD_START] ?: "" }
    val periodEnd: Flow<String> = dataStore.data.map { it[PERIOD_END] ?: "" }

    suspend fun setPeriodStart(value: String) {
        dataStore.edit { it[PERIOD_START] = value }
    }

    suspend fun setPeriodEnd(value: String) {
        dataStore.edit { it[PERIOD_END] = value }
    }

    // Currency
    val currencyCode: Flow<String> = dataStore.data.map { it[CURRENCY_CODE] ?: "BDT" }
    val currencySymbol: Flow<String> = dataStore.data.map { it[CURRENCY_SYMBOL] ?: "TK" }
    val currencyCustom: Flow<String> = dataStore.data.map { it[CURRENCY_CUSTOM] ?: "" }

    suspend fun setCurrencyCode(code: String) {
        dataStore.edit { it[CURRENCY_CODE] = code }
    }

    suspend fun setCurrencySymbol(symbol: String) {
        dataStore.edit { it[CURRENCY_SYMBOL] = symbol }
    }

    suspend fun setCurrencyCustom(custom: String) {
        dataStore.edit { it[CURRENCY_CUSTOM] = custom }
    }

    // Theme
    val themeMode: Flow<String> = dataStore.data.map { it[THEME_MODE] ?: "system" }

    suspend fun setThemeMode(mode: String) {
        dataStore.edit { it[THEME_MODE] = mode }
    }

    // Residue
    val residueMethod: Flow<String> = dataStore.data.map { it[RESIDUE_METHOD] ?: "ask" }
    val rememberResidue: Flow<Boolean> = dataStore.data.map { it[REMEMBER_RESIDUE] ?: false }

    suspend fun setResidueMethod(method: String) {
        dataStore.edit { it[RESIDUE_METHOD] = method }
    }

    suspend fun setRememberResidue(remember: Boolean) {
        dataStore.edit { it[REMEMBER_RESIDUE] = remember }
    }

    // Overspend
    val hideOverspend: Flow<Boolean> = dataStore.data.map { it[HIDE_OVERSPEND] ?: false }

    suspend fun setHideOverspend(hide: Boolean) {
        dataStore.edit { it[HIDE_OVERSPEND] = hide }
    }

    // Onboarding
    val onboardingDone: Flow<Boolean> = dataStore.data.map { it[ONBOARDING_DONE] ?: false }

    suspend fun setOnboardingDone(done: Boolean) {
        dataStore.edit { it[ONBOARDING_DONE] = done }
    }

    // Last known date
    val lastKnownDate: Flow<String> = dataStore.data.map { it[LAST_KNOWN_DATE] ?: "" }

    suspend fun setLastKnownDate(date: String) {
        dataStore.edit { it[LAST_KNOWN_DATE] = date }
    }

    // Sync getters for widgets
    suspend fun getPeriodStartSync(): String = dataStore.data.first()[PERIOD_START] ?: ""
    suspend fun getPeriodEndSync(): String = dataStore.data.first()[PERIOD_END] ?: ""
    suspend fun getCurrencySymbolSync(): String = dataStore.data.first()[CURRENCY_SYMBOL] ?: "TK"

    // Clear all
    suspend fun clearAll() {
        dataStore.edit { it.clear() }
    }
}
