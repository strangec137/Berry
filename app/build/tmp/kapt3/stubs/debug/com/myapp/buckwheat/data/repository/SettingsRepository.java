package com.myapp.buckwheat.data.repository;

import com.myapp.buckwheat.data.local.AppPreferences;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u001c\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u001c\u001a\u00020\u001dH\u00a6@\u00a2\u0006\u0002\u0010\u001eJ\u000e\u0010\u001f\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u001eJ\u000e\u0010 \u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u001eJ\u000e\u0010!\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010$J\u0016\u0010\'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010$J\u0016\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010+J\u0016\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010$J\u0016\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010+J\u0016\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010$J\u0016\u00102\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010$J\u0016\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010+J\u0016\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010$J\u0016\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010$R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u0006R\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0006R\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0006R\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0006R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0006R\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0006R\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0006R\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0006\u00a8\u00069"}, d2 = {"Lcom/myapp/buckwheat/data/repository/SettingsRepository;", "", "currencyCode", "Lkotlinx/coroutines/flow/Flow;", "", "getCurrencyCode", "()Lkotlinx/coroutines/flow/Flow;", "currencyCustom", "getCurrencyCustom", "currencySymbol", "getCurrencySymbol", "hideOverspend", "", "getHideOverspend", "lastKnownDate", "getLastKnownDate", "onboardingDone", "getOnboardingDone", "periodEnd", "getPeriodEnd", "periodStart", "getPeriodStart", "rememberResidue", "getRememberResidue", "residueMethod", "getResidueMethod", "themeMode", "getThemeMode", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrencySymbolSync", "getPeriodEndSync", "getPeriodStartSync", "setCurrencyCode", "code", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setCurrencyCustom", "custom", "setCurrencySymbol", "symbol", "setHideOverspend", "hide", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setLastKnownDate", "date", "setOnboardingDone", "done", "setPeriodEnd", "value", "setPeriodStart", "setRememberResidue", "remember", "setResidueMethod", "method", "setThemeMode", "mode", "app_debug"})
public abstract interface SettingsRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getPeriodStart();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getPeriodEnd();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getCurrencyCode();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getCurrencySymbol();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getCurrencyCustom();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getThemeMode();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getResidueMethod();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> getRememberResidue();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> getHideOverspend();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> getOnboardingDone();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getLastKnownDate();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setPeriodStart(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setPeriodEnd(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setCurrencyCode(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setCurrencySymbol(@org.jetbrains.annotations.NotNull()
    java.lang.String symbol, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setCurrencyCustom(@org.jetbrains.annotations.NotNull()
    java.lang.String custom, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setThemeMode(@org.jetbrains.annotations.NotNull()
    java.lang.String mode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setResidueMethod(@org.jetbrains.annotations.NotNull()
    java.lang.String method, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setRememberResidue(boolean remember, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setHideOverspend(boolean hide, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setOnboardingDone(boolean done, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setLastKnownDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPeriodStartSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPeriodEndSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrencySymbolSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}