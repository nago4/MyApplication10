package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class settei extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settei);
    }

    public void onButton3(View v) {
        Intent intent = new Intent(this, music.class);
        startActivity(intent);
    }

    public void onButton4(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}