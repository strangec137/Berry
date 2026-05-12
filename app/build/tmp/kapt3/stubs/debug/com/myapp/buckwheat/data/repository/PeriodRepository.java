package com.myapp.buckwheat.data.repository;

import com.myapp.buckwheat.data.local.dao.PeriodDao;
import com.myapp.buckwheat.data.local.entity.PeriodEntity;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H&J\u0010\u0010\t\u001a\u0004\u0018\u00010\bH\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/myapp/buckwheat/data/repository/PeriodRepository;", "", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPeriods", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/myapp/buckwheat/data/local/entity/PeriodEntity;", "getLatestPeriod", "insert", "", "period", "(Lcom/myapp/buckwheat/data/local/entity/PeriodEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface PeriodRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.local.entity.PeriodEntity period, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.myapp.buckwheat.data.local.entity.PeriodEntity>> getAllPeriods();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestPeriod(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.myapp.buckwheat.data.local.entity.PeriodEntity> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}