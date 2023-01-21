package com.example.electronicstation.screens;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.electronicstation.R;
import com.example.electronicstation.screens.adapters.SharePreferences;
import com.example.electronicstation.screens.modals.Station;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class AddStation extends AppCompatActivity {

    private EditText stationName, stationLevel, startTime, endTime, locationDistance;
    private Button addStation;
    Spinner locationSpinner;
    private String locationValue= null;
    private FirebaseFirestore db = null;
    private ArrayList<String> stationlocations = new ArrayList<String>();
    private SharePreferences sharePreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);
        setTitle("Add Station");

        String [] locations = getResources().getStringArray(R.array.locations);
        stationlocations.addAll(Arrays.asList(locations));


        initializeData();

     locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             locationValue = stationlocations.get(i);
         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });


        addStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stationName.getText().length()==0){
                    stationName.setError("Name is required");
                }else if(stationLevel.getText().length()==0){
                    stationLevel.setError("Level is required");
                }else if(startTime.getText().length()==0){
                    startTime.setError("Time is required");
                }else if(endTime.getText().length()==0){
                    endTime.setError("Time is required");
                }else if(locationValue==null){
                    Toast.makeText(getApplicationContext(),"Location is required",Toast.LENGTH_SHORT).show();
                }else{

                    Station station = new Station(
                            stationName.getText().toString(),
                            stationLevel.getText().toString(),
                            startTime.getText().toString(),
                            endTime.getText().toString(),
                            locationValue,
                            "2.3 km",
                            sharePreferences.readUserData("userName")
                            );


                    //Add data to Firestore
                    db.collection("stations")
                            .add(station)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.w(TAG, "Success!");
                                    Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                                    intent.putExtra("ADD_STATION", station);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }
            }
        });

    }

    private void initializeData (){
        db = FirebaseFirestore.getInstance();
        stationName = findViewById(R.id.station_Name);
        stationLevel = findViewById(R.id.station_Level);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        locationSpinner = findViewById(R.id.spinner1);

        addStation = findViewById(R.id.add_Station);
        sharePreferences = new SharePreferences(getApplicationContext());
    }
}