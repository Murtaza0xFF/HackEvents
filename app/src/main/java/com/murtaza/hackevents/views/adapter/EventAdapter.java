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

package com.murtaza.hackevents.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.murtaza.hackevents.R;
import com.murtaza.hackevents.model.Event;
import com.murtaza.hackevents.views.widgets.AppWidget;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class EventAdapter implements RemoteViewsService.RemoteViewsFactory {

    private Context context = null;
    RemoteViews row;
    public EventAdapter(Context context, Intent intent) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return AppWidget.filteredList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        row = new RemoteViews(context.getPackageName(), R.layout.widget_row);
        final Event event = AppWidget.filteredList.get(position);
        try {
            Bitmap b = Picasso.with(context).load(event.getThumbnail()).get();
            row.setImageViewBitmap(R.id.event_image, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("URL", position);
        row.setTextViewText(R.id.title, event.getTitle());
        row.setOnClickFillInIntent(R.id.widget_title, fillInIntent);
        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

    }
}
