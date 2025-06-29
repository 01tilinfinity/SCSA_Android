package com.scsa.goatsaeng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAlarm, btnMouse, btnStt, btnTts, btnTodo, btnRss, btnNfc, btnBeacon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlarm = findViewById(R.id.btnAlarm);
        btnMouse = findViewById(R.id.btnMouse);
        btnStt = findViewById(R.id.btnStt);
        btnTts = findViewById(R.id.btnTts);
        btnTodo = findViewById(R.id.btnTodo);
        btnRss = findViewById(R.id.btnRss);
        btnNfc = findViewById(R.id.btnNfc);
        btnBeacon = findViewById(R.id.btnBeacon);

        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmMainActivity.class);
                startActivity(intent);
            }
        });

        btnMouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MouseMainActivity.class);
                startActivity(intent);
            }
        });

        btnStt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SttMainActivity.class);
                startActivity(intent);
            }
        });

        btnTts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TtsMainActivity.class);
                startActivity(intent);
            }
        });

        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TodoActivity.class);
                startActivity(intent);
            }
        });

        btnRss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RssMainActivity.class);
                startActivity(intent);
            }
        });

        btnNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NfcActivity.class);
                startActivity(intent);
            }
        });

        btnBeacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BeaconActivity.class);
                startActivity(intent);
            }
        });
    }
}
