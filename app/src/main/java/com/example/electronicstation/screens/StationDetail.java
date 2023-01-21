package com.example.electronicstation.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.electronicstation.R;
import com.example.electronicstation.screens.modals.Station;

public class StationDetail extends AppCompatActivity {

    private TextView stationName, type, distance, startTime, endTime;
    Button payBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);
        setTitle("Booking");
        Station station = (Station) getIntent().getSerializableExtra("STATION_DETAIL");

        initializeData();

        stationName.setText(station.getName());
        type.setText("Level: "+ station.getStationLevel());
        distance.setText("Distance from your location: "+ station.getLocation());
        startTime.setText(station.getStartTime());
        endTime.setText(station.getEndTime());

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the payment action
            }
        });

    }
    private void initializeData(){
        stationName = findViewById(R.id.station_name);
        type = findViewById(R.id.type);
        distance = findViewById(R.id.location);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        payBtn = findViewById(R.id.payBtn);
    }
}