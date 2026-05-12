package com.myapp.buckwheat.data.repository;

import com.myapp.buckwheat.data.local.AppPreferences;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u001c\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001f\u001a\u00020 H\u0096@\u00a2\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010!J\u000e\u0010#\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010!J\u000e\u0010$\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010!J\u0016\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\'J\u0016\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\'J\u0016\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\'J\u0016\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010.J\u0016\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\'J\u0016\u00101\u001a\u00020 2\u0006\u00102\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010.J\u0016\u00103\u001a\u00020 2\u0006\u00104\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\'J\u0016\u00105\u001a\u00020 2\u0006\u00104\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\'J\u0016\u00106\u001a\u00020 2\u0006\u00107\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010.J\u0016\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\'J\u0016\u0010:\u001a\u00020 2\u0006\u0010;\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\'R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\tR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\tR\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\tR\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\tR\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\tR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\t\u00a8\u0006<"}, d2 = {"Lcom/myapp/buckwheat/data/repository/SettingsRepositoryImpl;", "Lcom/myapp/buckwheat/data/repository/SettingsRepository;", "prefs", "Lcom/myapp/buckwheat/data/local/AppPreferences;", "(Lcom/myapp/buckwheat/data/local/AppPreferences;)V", "currencyCode", "Lkotlinx/coroutines/flow/Flow;", "", "getCurrencyCode", "()Lkotlinx/coroutines/flow/Flow;", "currencyCustom", "getCurrencyCustom", "currencySymbol", "getCurrencySymbol", "hideOverspend", "", "getHideOverspend", "lastKnownDate", "getLastKnownDate", "onboardingDone", "getOnboardingDone", "periodEnd", "getPeriodEnd", "periodStart", "getPeriodStart", "rememberResidue", "getRememberResidue", "residueMethod", "getResidueMethod", "themeMode", "getThemeMode", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrencySymbolSync", "getPeriodEndSync", "getPeriodStartSync", "setCurrencyCode", "code", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setCurrencyCustom", "custom", "setCurrencySymbol", "symbol", "setHideOverspend", "hide", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setLastKnownDate", "date", "setOnboardingDone", "done", "setPeriodEnd", "value", "setPeriodStart", "setRememberResidue", "remember", "setResidueMethod", "method", "setThemeMode", "mode", "app_debug"})
public final class SettingsRepositoryImpl implements com.myapp.buckwheat.data.repository.SettingsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.local.AppPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> periodStart = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> periodEnd = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> currencyCode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> currencySymbol = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> currencyCustom = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> themeMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> residueMethod = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> rememberResidue = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> hideOverspend = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> onboardingDone = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> lastKnownDate = null;
    
    @javax.inject.Inject()
    public SettingsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.local.AppPreferences prefs) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getPeriodStart() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getPeriodEnd() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getCurrencyCode() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getCurrencySymbol() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getCurrencyCustom() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getThemeMode() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getResidueMethod() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> getRememberResidue() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> getHideOverspend() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> getOnboardingDone() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> getLastKnownDate() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setPeriodStart(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setPeriodEnd(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setCurrencyCode(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setCurrencySymbol(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setCurrencyCustom(@org.jetbrains.annotations.NotNull()
    java.lang.String custom, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setThemeMode(@org.jetbrains.annotations.NotNull()
    java.lang.String mode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setResidueMethod(@org.jetbrains.annotations.NotNull()
    java.lang.String method, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setRememberResidue(boolean remember, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setHideOverspend(boolean hide, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setOnboardingDone(boolean done, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setLastKnownDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPeriodStartSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPeriodEndSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCurrencySymbolSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}