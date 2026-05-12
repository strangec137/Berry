package com.myapp.buckwheat.widget.minimal;

import android.content.Context;
import androidx.glance.GlanceId;
import androidx.glance.GlanceModifier;
import androidx.glance.appwidget.GlanceAppWidget;
import androidx.glance.appwidget.GlanceAppWidgetReceiver;
import androidx.glance.layout.*;
import androidx.glance.text.FontWeight;
import androidx.glance.text.TextStyle;
import com.myapp.buckwheat.data.local.AppPreferences;
import com.myapp.buckwheat.data.local.AppDatabase;
import androidx.room.Room;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/myapp/buckwheat/widget/minimal/MinimalWidget;", "Landroidx/glance/appwidget/GlanceAppWidget;", "()V", "provideGlance", "", "context", "Landroid/content/Context;", "id", "Landroidx/glance/GlanceId;", "(Landroid/content/Context;Landroidx/glance/GlanceId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class MinimalWidget extends androidx.glance.appwidget.GlanceAppWidget {
    
    public MinimalWidget() {
        super(0);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object provideGlance(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.glance.GlanceId id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}