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

package com.murtaza.hackevents.di.modules;



import com.murtaza.hackevents.di.scopes.ActivityScope;
import com.murtaza.hackevents.presenter.MainPresenter;
import com.murtaza.hackevents.presenter.MainPresenterImpl;
import com.murtaza.hackevents.service.EventAPI;
import com.murtaza.hackevents.views.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainView mainView;
    private EventAPI eventAPI;

    public MainActivityModule(MainView mainView) {

        this.mainView = mainView;
        this.eventAPI = eventAPI;
    }

    @ActivityScope
    @Provides
    public MainPresenter providesMainPresenter(){
        return new MainPresenterImpl(mainView);
    }
}
