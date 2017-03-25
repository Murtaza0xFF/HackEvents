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

package com.murtaza.hackevents.service;

import com.murtaza.hackevents.model.Event;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;


public class FetchData {
    public ArrayList<Event> getResponse(EventAPI eventApi) {
        Call<com.murtaza.hackevents.model.Response> call = eventApi.getEvents();
        ArrayList<Event> responseList = new ArrayList<>();
        try {
            responseList = (ArrayList<Event>) call.execute().body().getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        call.enqueue(new Callback<com.murtaza.hackevents.model.Response>() {
//            @Override
//            public void onResponse(Call<com.murtaza.hackevents.model.Response> call, retrofit2.Response<com.murtaza.hackevents.model.Response> response) {
//                for (Event event : response.body().getResponse()){
//                    responsesList.add(event);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<com.murtaza.hackevents.model.Response> call, Throwable t) {
//            }
//        });
        return responseList;
    }
}
