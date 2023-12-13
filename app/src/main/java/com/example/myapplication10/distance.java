package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        EditText DistanC = findViewById(R.id.Distanc);
        String DISTANCE = DistanC.getText().toString();//文字列として取得

        try {
            double inputNumber = Double.parseDouble(DISTANCE);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("INPUT_NUMBER", inputNumber);
            startActivity(intent);
        } catch (NumberFormatException e) {
            // 数値に変換できない場合のエラー処理
            e.printStackTrace();
        }
    }
    public void onButton14(View view) {
        Intent intent = new Intent(this,settei.class);
        startActivity(intent);
    }
}