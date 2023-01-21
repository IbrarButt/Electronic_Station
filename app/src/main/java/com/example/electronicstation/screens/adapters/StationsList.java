package com.example.electronicstation.screens.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.electronicstation.R;
import com.example.electronicstation.screens.modals.Station;

import java.util.ArrayList;

public class StationsList extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Station> stationArrayList = new ArrayList<Station>();
    public StationsList(Context context,ArrayList<Station> stationArrayList){
        this.context = context;
        this.stationArrayList = stationArrayList;
        layoutInflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return stationArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.stations_list_item, null);
        TextView distanceText = view.findViewById(R.id.distance_text);
        TextView levelText = view.findViewById(R.id.level_text);
        TextView stationName= view.findViewById(R.id.station_name);

        stationName.setText(stationArrayList.get(i).getName());
        levelText.setText(stationArrayList.get(i).getStationLevel());
        distanceText.setText(stationArrayList.get(i).getLocation());

        return view;
    }
}
