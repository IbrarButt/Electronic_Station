package com.example.electronicstation.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electronicstation.R;
import com.example.electronicstation.screens.adapters.StationsList;
import com.example.electronicstation.screens.modals.Station;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView = null;
    private TextView emptyText = null;
    private ListView stationsListView = null;
    private ArrayList<Station> stations = new ArrayList<Station>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Stations");
        //Empty state
        imageView = findViewById(R.id.icon);
        emptyText = findViewById(R.id.empty_text);
        stationsListView = findViewById(R.id.station_list);

        //Add Data
        addData();

        if(stations.size()>0) {
            imageView.setVisibility(View.GONE);
            emptyText.setVisibility(View.GONE);
            stationsListView.setVisibility(View.VISIBLE);
            StationsList stationsList = new StationsList(getApplicationContext(), stations);
            stationsListView.setAdapter(stationsList);
        }else{
            stationsListView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        }


    }
    private void addData (){
        stations.add(new Station("Ali Station","2","8:00","9:00","2.3km"));
        stations.add(new Station("Khalid Station","1","8:00","9:00","2.3km"));
        stations.add(new Station("Fattah Station","3","8:00","9:00","2.3km"));
        stations.add(new Station("makkah Station","1","8:00","9:00","2.3km"));
        stations.add(new Station("Falcon Station","3","8:00","9:00","2.3km"));
        stations.add(new Station("Waseem Station","2","8:00","9:00","2.3km"));

    }
    }