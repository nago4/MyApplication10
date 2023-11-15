package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class distance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
    }
    public void onButton13(View v) {
        EditText Distanc = (EditText) findViewById(R.id.Distanc);
        String DISTSNCE = Distanc.getText().toString();//文字列として取得
    }
}