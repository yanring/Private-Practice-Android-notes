package com.example.yanring.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by Yanring on 2016/2/18.
 */
public class TestWidget extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null && TextUtils.equals(intent.getAction(),"widget_button_action")){
            Log.i("widget_button_action","be clicked");
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.layout_widget);
            remoteViews.setTextViewText(R.id.textView, "be clicked");

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context,TestWidget.class);
            appWidgetManager.updateAppWidget(componentName,remoteViews);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.layout_widget);

        Intent intent = new Intent();
        intent.setClass(context,TestWidget.class);//发给自己
        intent.setAction("widget_button_action");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);

        remoteViews.setOnClickPendingIntent(R.id.widget_button,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
    }
}

