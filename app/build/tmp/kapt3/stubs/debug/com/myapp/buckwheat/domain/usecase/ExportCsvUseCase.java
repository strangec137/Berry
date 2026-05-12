package com.myapp.buckwheat.domain.usecase;

import android.content.Context;
import android.content.Intent;
import androidx.core.content.FileProvider;
import com.myapp.buckwheat.data.local.entity.TransactionEntity;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.io.FileWriter;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0086B\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/myapp/buckwheat/domain/usecase/ExportCsvUseCase;", "", "context", "Landroid/content/Context;", "transactionRepository", "Lcom/myapp/buckwheat/data/repository/TransactionRepository;", "(Landroid/content/Context;Lcom/myapp/buckwheat/data/repository/TransactionRepository;)V", "invoke", "Landroid/content/Intent;", "startDate", "", "endDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ExportCsvUseCase {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository = null;
    
    @javax.inject.Inject()
    public ExportCsvUseCase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super android.content.Intent> $completion) {
        return null;
    }
}