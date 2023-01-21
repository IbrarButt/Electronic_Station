package com.example.electronicstation.screens;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.electronicstation.R;
import com.example.electronicstation.screens.adapters.SharePreferences;
import com.example.electronicstation.screens.adapters.StationsList;
import com.example.electronicstation.screens.modals.Station;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView = null;
    private TextView emptyText = null;
    private ListView stationsListView = null;
    private ArrayList<Station> stations = new ArrayList<Station>();
    private FloatingActionButton floatingActionButton;
    private SharePreferences sharePreferences;
    private FirebaseFirestore db;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
              case R.id.logout:
                  showDialog();
                  return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Stations");
        //Empty state
        imageView = findViewById(R.id.icon);
        emptyText = findViewById(R.id.empty_text);
        stationsListView = findViewById(R.id.station_list);
        floatingActionButton = findViewById(R.id.fab);
        sharePreferences = new SharePreferences(getApplicationContext());
        db = FirebaseFirestore.getInstance();
        if(sharePreferences.isOwner()){
            floatingActionButton.setVisibility(View.VISIBLE);
        }

        //Add Data
        loadData();

        //Floating Action Data
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddStation.class);
                startActivity(intent);
            }
        });



        stationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),StationDetail.class);
                intent.putExtra("STATION_DETAIL",stations.get(position));
                startActivity(intent);
            }
        });


    }
    private void showDialog (){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to logout?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(),AuthScreen.class);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    private void handleEmptyState(){
        //Handle the empty state
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
    private void loadData (){
        Station station = (Station) getIntent().getSerializableExtra("ADD_STATION");
        if(station!= null){
            stations.add(station);
        }

        if(sharePreferences.isOwner()) {
            CollectionReference questionRef = db.collection("stations");
            questionRef.whereEqualTo("name", sharePreferences.readUserData("userName")).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Station station = new Station((String) document.getString("name"),
                                (String) document.getString("stationLevel"),
                                (String) document.getString("startTime"),
                                (String) document.getString("endTime"),
                                (String) document.getString("location"),
                                (String) document.getString("locationDistance"),
                                (String) document.getString("userName"));
                        stations.add(station);
                    }
                    handleEmptyState();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            CollectionReference questionRef = db.collection("stations");
            questionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Station station = new Station((String) document.getString("name"),
                                (String) document.getString("stationLevel"),
                                (String) document.getString("startTime"),
                                (String) document.getString("endTime"),
                                (String) document.getString("location"),
                                (String) document.getString("locationDistance"),
                                (String) document.getString("userName"));
                        stations.add(station);
                    }
                    handleEmptyState();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}