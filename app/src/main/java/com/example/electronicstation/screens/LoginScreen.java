package com.example.electronicstation.screens;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.electronicstation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    private EditText userName = null;
    private EditText userPassword = null;
    private Button login_btn = null;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().setTitle("Login");

        initiateData();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onSubmitHandler();
            }
        });


    }
    private void onSubmitHandler (){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

        if(userName.getText().length()==0){
            userName.setError("Name is required");
        }else if(userPassword.getText().length()==0){
            userPassword.setError("Password is required");
        }else{
//            CollectionReference questionRef = db.collection("users");
//            questionRef.whereEqualTo("userName", userName.getText().toString()).whereEqualTo("userPassword",userPassword.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                @Override
//                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                    Log.d(TAG,"Success!");
//                    DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
//                    Log.d(TAG, (String) document.get("mobileNumber"));
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();
//                }
//            });
        }

    }
    private void initiateData (){
        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.user_pass);
        login_btn = findViewById(R.id.login_btn);
        db = FirebaseFirestore.getInstance();
    }

}