package com.myapp.buckwheat.data.repository;

import com.myapp.buckwheat.data.local.dao.PeriodDao;
import com.myapp.buckwheat.data.local.entity.PeriodEntity;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u0096@\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tH\u0016J\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0096@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/myapp/buckwheat/data/repository/PeriodRepositoryImpl;", "Lcom/myapp/buckwheat/data/repository/PeriodRepository;", "periodDao", "Lcom/myapp/buckwheat/data/local/dao/PeriodDao;", "(Lcom/myapp/buckwheat/data/local/dao/PeriodDao;)V", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPeriods", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/myapp/buckwheat/data/local/entity/PeriodEntity;", "getLatestPeriod", "insert", "", "period", "(Lcom/myapp/buckwheat/data/local/entity/PeriodEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class PeriodRepositoryImpl implements com.myapp.buckwheat.data.repository.PeriodRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.local.dao.PeriodDao periodDao = null;
    
    @javax.inject.Inject()
    public PeriodRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.local.dao.PeriodDao periodDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.local.entity.PeriodEntity period, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.myapp.buckwheat.data.local.entity.PeriodEntity>> getAllPeriods() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLatestPeriod(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.myapp.buckwheat.data.local.entity.PeriodEntity> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}