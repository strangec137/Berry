package com.myapp.buckwheat.data.repository

import com.myapp.buckwheat.data.local.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface SettingsRepository {
    val periodStart: Flow<String>
    val periodEnd: Flow<String>
    val currencyCode: Flow<String>
    val currencySymbol: Flow<String>
    val currencyCustom: Flow<String>
    val themeMode: Flow<String>
    val residueMethod: Flow<String>
    val rememberResidue: Flow<Boolean>
    val hideOverspend: Flow<Boolean>
    val onboardingDone: Flow<Boolean>
    val lastKnownDate: Flow<String>

    suspend fun setPeriodStart(value: String)
    suspend fun setPeriodEnd(value: String)
    suspend fun setCurrencyCode(code: String)
    suspend fun setCurrencySymbol(symbol: String)
    suspend fun setCurrencyCustom(custom: String)
    suspend fun setThemeMode(mode: String)
    suspend fun setResidueMethod(method: String)
    suspend fun setRememberResidue(remember: Boolean)
    suspend fun setHideOverspend(hide: Boolean)
    suspend fun setOnboardingDone(done: Boolean)
    suspend fun setLastKnownDate(date: String)
    suspend fun getPeriodStartSync(): String
    suspend fun getPeriodEndSync(): String
    suspend fun getCurrencySymbolSync(): String
    suspend fun clearAll()
}

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val prefs: AppPreferences
) : SettingsRepository {

    override val periodStart: Flow<String> = prefs.periodStart
    override val periodEnd: Flow<String> = prefs.periodEnd
    override val currencyCode: Flow<String> = prefs.currencyCode
    override val currencySymbol: Flow<String> = prefs.currencySymbol
    override val currencyCustom: Flow<String> = prefs.currencyCustom
    override val themeMode: Flow<String> = prefs.themeMode
    override val residueMethod: Flow<String> = prefs.residueMethod
    override val rememberResidue: Flow<Boolean> = prefs.rememberResidue
    override val hideOverspend: Flow<Boolean> = prefs.hideOverspend
    override val onboardingDone: Flow<Boolean> = prefs.onboardingDone
    override val lastKnownDate: Flow<String> = prefs.lastKnownDate

    override suspend fun setPeriodStart(value: String) = prefs.setPeriodStart(value)
    override suspend fun setPeriodEnd(value: String) = prefs.setPeriodEnd(value)
    override suspend fun setCurrencyCode(code: String) = prefs.setCurrencyCode(code)
    override suspend fun setCurrencySymbol(symbol: String) = prefs.setCurrencySymbol(symbol)
    override suspend fun setCurrencyCustom(custom: String) = prefs.setCurrencyCustom(custom)
    override suspend fun setThemeMode(mode: String) = prefs.setThemeMode(mode)
    override suspend fun setResidueMethod(method: String) = prefs.setResidueMethod(method)
    override suspend fun setRememberResidue(remember: Boolean) = prefs.setRememberResidue(remember)
    override suspend fun setHideOverspend(hide: Boolean) = prefs.setHideOverspend(hide)
    override suspend fun setOnboardingDone(done: Boolean) = prefs.setOnboardingDone(done)
    override suspend fun setLastKnownDate(date: String) = prefs.setLastKnownDate(date)
    override suspend fun getPeriodStartSync(): String = prefs.getPeriodStartSync()
    override suspend fun getPeriodEndSync(): String = prefs.getPeriodEndSync()
    override suspend fun getCurrencySymbolSync(): String = prefs.getCurrencySymbolSync()
    override suspend fun clearAll() = prefs.clearAll()
}
