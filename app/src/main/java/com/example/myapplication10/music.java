package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class music extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
    }
    public void onButton5(View view) {
        int selectedSoundResourceId = R.raw.alarm1;
        SharedPreferences sharedPreferences = getSharedPreferences("AlarmSound", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("selectedSoundResourceId", selectedSoundResourceId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        }
        ((TextView) findViewById(R.id.otokekka)).setText("alarm1に設定しました。");
    }
    public void onButton6(View view) {
        int selectedSoundResourceId = R.raw.alarm2;
        SharedPreferences sharedPreferences = getSharedPreferences("AlarmSound", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("selectedSoundResourceId", selectedSoundResourceId);
        editor.apply();
        ((TextView) findViewById(R.id.otokekka)).setText("");
        ((TextView) findViewById(R.id.otokekka)).setText("alarm2に設定しました。");
    }

    public void onButton8(View view) {
        mediaPlayer = MediaPlayer.create(this,R.raw.alarm1);
        mediaPlayer.start();
    }
    public void onButton10(View v){
        // 再生終了
        mediaPlayer.stop();
        // リセット
        mediaPlayer.reset();
        // アクティビティを終了
        //finish();
        // リソースの解放
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void onButton9(View view) {
        mediaPlayer = MediaPlayer.create(this,R.raw.alarm2);
        mediaPlayer.start();
    }
    public void onButton11(View v){
        // 再生終了
        mediaPlayer.stop();
        // リセット
        mediaPlayer.reset();
        // アクティビティを終了
        //finish();
        // リソースの解放
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void onButton7(View view) {
        Intent intent = new Intent(this,settei.class);
        startActivity(intent);
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
