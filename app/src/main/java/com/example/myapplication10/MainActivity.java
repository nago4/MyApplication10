package com.example.myapplication10;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import android.Manifest;
import android.view.View;




//.自分の現在位置（緯度、経度）を取得.

public class MainActivity extends AppCompatActivity implements LocationListener {   //Activityに LocationListener を登録

    LocationManager locationManager;  //後で位置情報の取得や監視などを行う際に、locationManagerオブジェクトを使用できる
    Location currentLocation; // Locationオブジェクトを保持するフィールド(これを書いたことで、ボタンの設定の時に、locAやlocBの変数に代入できた。）

    //permissionチェックのリクエスト結果を受け取る
    private final ActivityResultLauncher<String>
            requestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    locationStart();
                } else {
                    Toast toast = Toast.makeText(this,
                            "これ以上なにもできません", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //permissionが既に許可されているかの確認
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            locationStart();
        }
    }

    private void locationStart() {
        Log.d("debug", "locationStart()");

        // LocationManager インスタンス生成
        locationManager =
                (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager != null && locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)) {

            Log.d("debug", "location manager Enabled");
        } else {
            // GPSを設定するように促す
            Intent settingsIntent =
                    new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
            Log.d("debug", "not gpsEnable, startActivity");
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

            Log.d("debug", "checkSelfPermission false");
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location; // フィールドにLocationを設定

        // 緯度の表示
        TextView textView1 = findViewById(R.id.text_view1);
        String str1 = "緯度:" + currentLocation.getLatitude();
        textView1.setText(str1);


        // 経度の表示
        TextView textView2 = findViewById(R.id.text_view2);
        String str2 = "経度:" + currentLocation.getLongitude();
        textView2.setText(str2);

    }

    //京阪の駅名とその座標の保管
    String[][] station = {
            //①駅名　②駅の緯度　③駅の経度w
            {"淀屋橋", "北浜", "天満橋", "京橋", "野江", "関目", "森小路", "千林", "滝井", "土居", "守口市", "西三荘", "門真市", "古川橋", "大和田", "萱島", "寝屋川市", "香里園", "光善寺", "枚方公園", "枚方市", "御殿山", "牧野", "樟葉", "橋本", "石清水八幡宮", "淀", "中書島", "伏見桃山", "丹波橋", "墨染", "藤森", "龍谷大前深草", "伏見稲荷", "鳥羽街道", "東福寺", "七条", "清水五条", "祇園四条", "三条", "神宮丸太町", "出町柳"},
            {"34.692321", "34.691588", "34.6902", "34.697043", "34.707201", "34.712495", "34.719591", "34.724219", "34.727682", "34.730815", "34.735373", "34.737448", "34.738215", "34.740017", "34.743031", "34.747381", "34.763988", "34.784677", "34.797917", "34.811199", "34.816123", "34.828944", "34.843526", "34.861977", "34.88167", "34.884651", "34.906819", "34.926878", "34.932616", "34.938671", "34.948212", "34.956775", "34.964247", "34.96683", "34.973168", "34.981206", "34.989322", "34.996107", "35.003812", "35.009114", "35.017669", "35.029613"},
            {"135.501004", "135.506634", "135.516633", "135.532171", "135.543445", "135.546803", "135.551566", "135.55511", "135.557493", "135.560204", "135.565179", "135.575936", "135.583269", "135.591334", "135.602589", "135.611157", "135.620667", "135.631035", "135.630227", "135.639401", "135.648417", "135.653952", "135.665412", "135.67528", "135.684101", "135.69996", "135.721457", "135.760015", "135.771123", "135.765412", "135.769015", "135.770065", "135.770154", "135.770849", "135.769929", "135.770177", "135.767846", "135.768527", "135.771976", "135.772274", "135.772162", "135.77244"}
    };


    public void onButton2(View v) {
        //駅名が入力され、決定ボタンが押されたときのその駅の緯度経度の表示。
        //入力された文字列の取得。id「station]にその入力されたものを文字列として取得。入力された駅名の緯度経度を変数「goalA」と「goalB」に代入。もし辞書になかったら、「このアプリは京阪以外の駅名に対応していません。」と表示する。
        EditText Station = (EditText) findViewById(R.id.station);
        String Station1 = Station.getText().toString();//文字列として取得
        boolean isStationFound = false;
        // goalA と goalB をブロックの外で宣言
        double goalA = 0.0;
        double goalB = 0.0;

        //入力された「station」に対応する緯度経度の代入
        for (String[] stationInfo : station) {
            for (String stationName : stationInfo) {
                if (Station1.equals(stationName)) {
                    isStationFound = true;
                    for (int i = 0; i < station[0].length; i++) {
                        if (station[0][i].equals(Station1)) {
                            goalA = Double.parseDouble(station[1][i]);
                            goalB = Double.parseDouble(station[2][i]);
                        }
                    }

                    //自位置の緯度経度の代入と距離の出力
                    double locA = currentLocation.getLatitude();
                    double locB = currentLocation.getLongitude();
                    double distance = Distance(locA, locB, goalA, goalB);
                    String distanceText = "目的地までの距離: " + distance + " km";
                    TextView distanceTextView = findViewById(R.id.distance);
                    distanceTextView.setText(distanceText);


                    Intent intent = getIntent();
                    if (intent != null) {
                        double receivedNumber = intent.getDoubleExtra("INPUT_NUMBER", 0.0);
                        Log.d("MainActivity", "Received number: " + receivedNumber);
                    }
                    double receivedNumber = intent.getDoubleExtra("distanc", 0.0);
                    //目的地から５００メートル以内に入った時,画面遷移が起こる。
                    if (distance < receivedNumber) {
                        Intent newIntent = new Intent(this, rennraku.class);
                        startActivity(newIntent);
                    } else {
                    }
                    break;
                }
            }
            if (isStationFound) {
                break;
            }
        }

        if (isStationFound) {
            ((TextView) findViewById(R.id.elseD)).setText("");
        } else {
            ((TextView) findViewById(R.id.elseD)).setText("このアプリは京阪以外の駅名に対応していません。");
        }
    }

    public void onButton1(View v) {
        Intent intent = new Intent(this, settei.class);
        startActivity(intent);
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    //任意の座標との距離を求める関数(真球近似法)
    private static double Distance(double locA,double locB,double goalA,double goalB){
        double pi = Math.PI;
        double distance = 6371 * Math.acos(
                Math.cos(locA/180*pi) * Math.cos((goalB - locB)/180*pi) * Math.cos(goalA/180*pi) +
                        Math.sin(locA/180*pi) * Math.sin(goalA/180*pi));
        return distance;
    }
}
