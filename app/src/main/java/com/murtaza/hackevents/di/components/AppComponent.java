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

package com.murtaza.hackevents.di.components;


import com.murtaza.hackevents.HackEvents;
import com.murtaza.hackevents.di.modules.AppModule;
import com.murtaza.hackevents.di.modules.MainActivityModule;
import com.murtaza.hackevents.views.widgets.AppWidget;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
    AppModule.class,
})
@Singleton
public interface AppComponent {

    void inject(HackEvents hackEvents);
    void inject(AppWidget appWidget);
    MainActivityComponent plus(MainActivityModule mainActivityModule);

}
