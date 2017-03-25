/*
 *
 *     Copyright (C)  2017  Murtaza Akbari
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.murtaza.hackevents.views.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.murtaza.hackevents.HackEvents;
import com.murtaza.hackevents.R;
import com.murtaza.hackevents.model.Event;
import com.murtaza.hackevents.service.EventAPI;
import com.murtaza.hackevents.service.UpdateWidgetService;
import com.murtaza.hackevents.utils.WidgetUpdateUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class AppWidget extends AppWidgetProvider {

    @Inject
    EventAPI eventAPI;
    public static ArrayList<Event> filteredList = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        ((HackEvents) context.getApplicationContext()).getAppComponent().inject(this);
        Bundle extras = intent.getExtras();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(), AppWidget.class.getName());
        int[] appWidgetIDs = appWidgetManager.getAppWidgetIds(thisAppWidget);
        onUpdate(context, appWidgetManager, appWidgetIDs);


    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIDs) {

        for (int i = 0; i < appWidgetIDs.length; i++) {
            final RemoteViews[] remoteViews = new RemoteViews[1];
            final int id = appWidgetIDs[i];
            final WidgetUpdateUtils widgetUpdate = new WidgetUpdateUtils();
            widgetUpdate.updateWidgetListView(eventAPI)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ArrayList<Event>>() {
                    @Override
                    public void accept(ArrayList<Event> eventList) throws Exception {
                        widgetUpdate.filterList();
                        remoteViews[0] = new RemoteViews(
                            context.getPackageName(), R.layout.widget_layout);
                        remoteViews[0].setRemoteAdapter(R.id.widget_list,
                            getIntent(context, id, remoteViews[0]));
                        updateWidget(appWidgetManager, id, remoteViews, context);
                    }
                });
        }
        super.onUpdate(context, appWidgetManager, appWidgetIDs);
    }

    private Intent getIntent(Context context, int id, RemoteViews remoteViews) {

        Intent intentSync = new Intent(context, AppWidget.class);
        intentSync.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        PendingIntent pendingSync = PendingIntent.getBroadcast(context, 0, intentSync, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.sync, pendingSync);

        Intent intent = new Intent(context, UpdateWidgetService.class);
        intent.setData(Uri.fromParts("content", String.valueOf(id), null));
        return intent;
    }

    private void updateWidget(AppWidgetManager appWidgetManager, int id,
                              RemoteViews[] remoteViews, Context context) {
        ComponentName component = new ComponentName(context, AppWidget.class);
        appWidgetManager.notifyAppWidgetViewDataChanged(id, R.id.widget_list);
        appWidgetManager.updateAppWidget(component, remoteViews[0]);
    }


}
