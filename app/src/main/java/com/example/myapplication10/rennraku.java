package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class rennraku extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rennraku);
        SharedPreferences sharedPreferences = getSharedPreferences("AlarmSound", MODE_PRIVATE);
        int selectedSoundResourceId = sharedPreferences.getInt("selectedSoundResourceId", R.raw.alarm1);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, selectedSoundResourceId);

        // アラーム音を再生
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // アクティビティが破棄されたらメディアプレーヤーを解放
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void onButton1(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}





