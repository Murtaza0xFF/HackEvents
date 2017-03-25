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

package com.murtaza.hackevents;

import android.app.Application;

import com.murtaza.hackevents.di.components.AppComponent;
import com.murtaza.hackevents.di.components.DaggerAppComponent;
import com.murtaza.hackevents.di.modules.AppModule;

public class HackEvents extends Application {

    private AppComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
        applicationComponent.inject(this);
    }

    public AppComponent getAppComponent(){
        return applicationComponent;
    }
}
