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

package com.murtaza.hackevents.utils;

import com.murtaza.hackevents.model.Event;
import com.murtaza.hackevents.service.EventAPI;
import com.murtaza.hackevents.service.FetchData;
import com.murtaza.hackevents.views.widgets.AppWidget;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;



public class WidgetUpdateUtils {

    private ArrayList<Event> eventList;

    public void filterList(){
        AppWidget.filteredList.clear();
        for (Event event : eventList) {
            if (!event.getCollege()) {
                Event filteredEvent = new Event();
                filteredEvent.setThumbnail(event.getThumbnail());
                filteredEvent.setTitle(event.getTitle());
                filteredEvent.setUrl(event.getUrl());
                AppWidget.filteredList.add(filteredEvent);
            }
        }
    }

    public Observable<ArrayList<Event>> updateWidgetListView(final EventAPI eventAPI) {
        return Observable.create(new ObservableOnSubscribe<ArrayList<Event>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<Event>> e) throws Exception {
                if (eventAPI != null) {
                    eventList = new FetchData().getResponse(eventAPI);
                    e.onNext(eventList);
                }
            }
        });
    }
}
