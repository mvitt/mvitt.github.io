package com.android.lewis.vitt.matthew.caloriequickcount;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button getStartedBtn;
    int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStartedBtn = (Button) findViewById(R.id.getStartedBtn);

        setNotificationAlarm();


        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DateActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setNotificationAlarm() {

        Intent intent = new Intent(this, NotificationReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_HALF_DAY, PendingIntent.getBroadcast(this, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT));


    }
}
