package com.scsa.goatsaeng;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import com.scsa.goatsaeng.databinding.ActivityAlarmMainBinding;

public class AlarmMainActivity extends AppCompatActivity {

    AlarmManager manager;

    Calendar cal = Calendar.getInstance();
    int year = 0;
    int month = 0;
    int day = 0;
    int hour = 0;
    int min = 0;

    private static String pad(int c) {
        return String.format("%02d", c);
    }

    private ActivityAlarmMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlarmMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkStartPermissionRequest();
    }

    public void checkStartPermissionRequest() {
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 1000);
            Toast.makeText(this, "권한을 허용해 주세요.", Toast.LENGTH_SHORT).show();
        } else {
            initEvent();
        }
    }

    private void initEvent() {
        binding.reg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int after = Integer.parseInt(binding.elap.getText().toString());
                long now = SystemClock.elapsedRealtime();
                long atTime = now + (after * 1000);
                Intent intent = new Intent();
                intent.setClass(AlarmMainActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, 101, intent, PendingIntent.FLAG_IMMUTABLE);
                manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, atTime, pendingIntent);
                Toast.makeText(AlarmMainActivity.this, after + "초 후 알람등록", Toast.LENGTH_SHORT).show();
            }
        });

        binding.cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AlarmMainActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, 101, intent, PendingIntent.FLAG_IMMUTABLE);
                Toast.makeText(AlarmMainActivity.this, "알람 해제", Toast.LENGTH_SHORT).show();
                manager.cancel(pendingIntent);
            }
        });

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AlarmMainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        binding.date.setText(year + "-" + pad(monthOfYear + 1) + "-" + pad(dayOfMonth));
                        AlarmMainActivity.this.year = year;
                        AlarmMainActivity.this.month = monthOfYear;
                        AlarmMainActivity.this.day = dayOfMonth;
                    }
                }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AlarmMainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.time.setText(pad(hourOfDay) + ":" + pad(minute));
                        AlarmMainActivity.this.hour = hourOfDay;
                        AlarmMainActivity.this.min = minute;
                    }
                }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true).show();
            }
        });

        binding.reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AlarmMainActivity.this.day == 0 || AlarmMainActivity.this.min == 0) {
                    Toast.makeText(AlarmMainActivity.this, "날짜와 시간을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                cal.set(Calendar.YEAR, AlarmMainActivity.this.year);
                cal.set(Calendar.MONTH, AlarmMainActivity.this.month);
                cal.set(Calendar.DAY_OF_MONTH, AlarmMainActivity.this.day);
                cal.set(Calendar.HOUR_OF_DAY, AlarmMainActivity.this.hour);
                cal.set(Calendar.MINUTE, AlarmMainActivity.this.min);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                long atTime = cal.getTimeInMillis();

                Intent intent = new Intent();
                intent.setClass(AlarmMainActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, 102, intent, PendingIntent.FLAG_IMMUTABLE);
                manager.set(AlarmManager.RTC_WAKEUP, atTime, pendingIntent);
                Toast.makeText(AlarmMainActivity.this, "알람등록", Toast.LENGTH_SHORT).show();
            }
        });

        binding.cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmMainActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, 102, intent, PendingIntent.FLAG_IMMUTABLE);
                Toast.makeText(AlarmMainActivity.this, "알람 해제", Toast.LENGTH_SHORT).show();
                manager.cancel(pendingIntent);
            }
        });
    }
}
