package com.myapp.buckwheat.domain.model;

import java.time.LocalDate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\n\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0015\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u0015\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003Jk\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\n2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010\u000e\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\'\u001a\u00020\rH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006("}, d2 = {"Lcom/myapp/buckwheat/domain/model/AnalyticsData;", "", "averageSpendPerDay", "", "totalSpendingCount", "", "maxSpend", "Lcom/myapp/buckwheat/domain/model/SpendRecord;", "minSpend", "dailySpending", "", "Ljava/time/LocalDate;", "tagBreakdown", "", "dailyBudget", "(DILcom/myapp/buckwheat/domain/model/SpendRecord;Lcom/myapp/buckwheat/domain/model/SpendRecord;Ljava/util/Map;Ljava/util/Map;D)V", "getAverageSpendPerDay", "()D", "getDailyBudget", "getDailySpending", "()Ljava/util/Map;", "getMaxSpend", "()Lcom/myapp/buckwheat/domain/model/SpendRecord;", "getMinSpend", "getTagBreakdown", "getTotalSpendingCount", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class AnalyticsData {
    private final double averageSpendPerDay = 0.0;
    private final int totalSpendingCount = 0;
    @org.jetbrains.annotations.Nullable()
    private final com.myapp.buckwheat.domain.model.SpendRecord maxSpend = null;
    @org.jetbrains.annotations.Nullable()
    private final com.myapp.buckwheat.domain.model.SpendRecord minSpend = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.time.LocalDate, java.lang.Double> dailySpending = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Double> tagBreakdown = null;
    private final double dailyBudget = 0.0;
    
    public AnalyticsData(double averageSpendPerDay, int totalSpendingCount, @org.jetbrains.annotations.Nullable()
    com.myapp.buckwheat.domain.model.SpendRecord maxSpend, @org.jetbrains.annotations.Nullable()
    com.myapp.buckwheat.domain.model.SpendRecord minSpend, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.time.LocalDate, java.lang.Double> dailySpending, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Double> tagBreakdown, double dailyBudget) {
        super();
    }
    
    public final double getAverageSpendPerDay() {
        return 0.0;
    }
    
    public final int getTotalSpendingCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.myapp.buckwheat.domain.model.SpendRecord getMaxSpend() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.myapp.buckwheat.domain.model.SpendRecord getMinSpend() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.time.LocalDate, java.lang.Double> getDailySpending() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Double> getTagBreakdown() {
        return null;
    }
    
    public final double getDailyBudget() {
        return 0.0;
    }
    
    public AnalyticsData() {
        super();
    }
    
    public final double component1() {
        return 0.0;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.myapp.buckwheat.domain.model.SpendRecord component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.myapp.buckwheat.domain.model.SpendRecord component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.time.LocalDate, java.lang.Double> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Double> component6() {
        return null;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.myapp.buckwheat.domain.model.AnalyticsData copy(double averageSpendPerDay, int totalSpendingCount, @org.jetbrains.annotations.Nullable()
    com.myapp.buckwheat.domain.model.SpendRecord maxSpend, @org.jetbrains.annotations.Nullable()
    com.myapp.buckwheat.domain.model.SpendRecord minSpend, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.time.LocalDate, java.lang.Double> dailySpending, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Double> tagBreakdown, double dailyBudget) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}