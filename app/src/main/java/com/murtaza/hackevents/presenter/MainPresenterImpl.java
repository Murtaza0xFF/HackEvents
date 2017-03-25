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

package com.murtaza.hackevents.presenter;


import com.murtaza.hackevents.model.Event;
import com.murtaza.hackevents.service.EventAPI;
import com.murtaza.hackevents.service.FetchData;
import com.murtaza.hackevents.utils.ConnectionStatus;
import com.murtaza.hackevents.views.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private EventAPI eventAPI;
    private ArrayList<Event> eventList;
    private List<Event> filteredList = new ArrayList<>();

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onStart(EventAPI eventAPI) {
        this.eventAPI = eventAPI;
        if (eventList != null) {
            eventList.clear();
        }
        if (filteredList !=null){
            filteredList.clear();
        }
        mainView.loader(true);
        checkConnectivity();
    }
    private void checkConnectivity(){
        if (ConnectionStatus.isNetworkAvailable(mainView)) {
            initRequest();
        }else{
            mainView.noConnectivity();
        }
    }

    @Override
    public void initRequest() {
        getData().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new Consumer<ArrayList<Event>>() {
                @Override
                public void accept(ArrayList<Event> events) throws Exception {
                    mainView.recyclerViewDataChange(events);
                }
            });
    }

    private List<Event> getfilteredList(){
        for (Event event : eventList) {
            if (!event.getCollege()) {
                Event filteredEvent = new Event();
                filteredEvent.setThumbnail(event.getThumbnail());
                filteredEvent.setTitle(event.getTitle());
                filteredEvent.setUrl(event.getUrl());
                filteredList.add(filteredEvent);
            }
        }
        return filteredList;
    }

    private Observable<ArrayList<Event>> getData(){
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

