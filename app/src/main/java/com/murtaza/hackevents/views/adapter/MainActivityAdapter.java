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
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.murtaza.hackevents.R;
import com.murtaza.hackevents.model.Event;
import com.murtaza.hackevents.views.MainView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {

    MainView mainView;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Event> eventList;

    public MainActivityAdapter(MainView mainView, Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
        layoutInflater = LayoutInflater.from(context);
        this.mainView = mainView;
    }

    @Override
    public MainActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.rv_item, parent, false);
        return new MainActivityViewHolder(view, this);
    }


    @Override
    public void onBindViewHolder(final MainActivityViewHolder holder, final int position) {
        holder.title.setText(eventList.get(position).getTitle());
        String URL = eventList.get(position).getThumbnail();
        try {
            Picasso.with(context).load(URL).error(R.drawable.appwidget_background).fit().centerCrop()
                .into(holder.thumbnail);
        } catch (IllegalArgumentException e) {
            holder.thumbnail.setImageResource(R.drawable.appwidget_background);
        }
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setData(List<Event> eventList) {
        this.eventList = eventList;
    }


    class MainActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView thumbnail;
        TextView title;

        public MainActivityViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.rv_thumbnail);
            title = (TextView) itemView.findViewById(R.id.rv_title);
            itemView.findViewById(R.id.card).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            if(v.getId() == R.id.card) {
                context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(eventList.get(position).getUrl())));
            }
        }

    }

}