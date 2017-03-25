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

package com.murtaza.hackevents.views;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.murtaza.hackevents.BuildConfig;
import com.murtaza.hackevents.HackEvents;
import com.murtaza.hackevents.R;
import com.murtaza.hackevents.di.modules.MainActivityModule;
import com.murtaza.hackevents.model.Event;
import com.murtaza.hackevents.presenter.MainPresenter;
import com.murtaza.hackevents.service.EventAPI;
import com.murtaza.hackevents.utils.GridSpacingItemDecoration;
import com.murtaza.hackevents.views.adapter.MainActivityAdapter;
import com.murtaza0xff.staywoke.WakeUp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;
    @Inject
    EventAPI eventAPI;
    RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityAdapter mainActivityAdapter;
    private StaggeredGridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (BuildConfig.DEBUG){
            WakeUp.init(this);
        }
        ((HackEvents) getApplication()).getAppComponent().plus(new MainActivityModule(this)).inject(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        setUpRecyclerView(new ArrayList<Event>());
        mainPresenter.onStart(eventAPI);
        swipeRefreshLayout.setOnRefreshListener(
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mainPresenter.onStart(eventAPI);
                }
            });
    }

    @Override
    public void setUpRecyclerView(List<Event> eventList) {
        mainActivityAdapter = new MainActivityAdapter(this, this, eventList);
        gridLayoutManager = new StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mainActivityAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 25, true));
    }

    @Override
    public void recyclerViewDataChange(List<Event> eventList){
        loader(false);
        recyclerView.getRecycledViewPool().clear();
        mainActivityAdapter.setData(eventList);
        mainActivityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void noConnectivity(){
        loader(false);
        Snackbar.make(swipeRefreshLayout, R.string.no_connectivity,
            Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void loader(final boolean status){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(status);
            }
        });
    }


}