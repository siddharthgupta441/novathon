package com.example.travelmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class rating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }
    public void submit(){
        finish();
    }
}