package com.example.electronicstation.screens;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.electronicstation.R;
import com.example.electronicstation.screens.modals.AuthUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterScreen extends AppCompatActivity {

    private EditText nameEditText = null;
    private EditText emailEditText = null;
    private EditText mobileEditText = null;
    private EditText passwordEditText = null;
    private RadioButton userRadioBtn, ownerRadioBtn;

    private Button reg_btn = null;
    private FirebaseFirestore db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        getSupportActionBar().setTitle("Register");

        //Initialize
        initiateData();
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitHandler();
            }
        });


    }

    private void onSubmitHandler (){
        if(nameEditText.getText().length()==0){
            nameEditText.setError("Name is required");
        }else if(emailEditText.getText().length()==0){
            emailEditText.setError("Email is required");
        }else if(mobileEditText.getText().length()==0){
           mobileEditText.setError("Number is required");
        }else if(passwordEditText.getText().length()==0){
            passwordEditText.setError("Password is required");
        }else{
            AuthUser authUser = new AuthUser(
                    nameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    mobileEditText.getText().toString(),
                    emailEditText.getText().toString(), userRadioBtn.isChecked());


            //Add data to Firestore
            db.collection("users")
                    .add(authUser)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.w(TAG, "Success!");
                            Intent intent =new Intent(getApplicationContext(),LoginScreen.class);
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
    private void initiateData (){
        nameEditText = findViewById(R.id.user_name);
        emailEditText = findViewById(R.id.user_email);
        mobileEditText = findViewById(R.id.user_mobile);
        passwordEditText = findViewById(R.id.user_pass);
        userRadioBtn = findViewById(R.id.userRadioBtn);
        ownerRadioBtn = findViewById(R.id.ownerRadioBtn);
        reg_btn = findViewById(R.id.reg_btn);

        db = FirebaseFirestore.getInstance();
    }
}