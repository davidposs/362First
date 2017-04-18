package com.example.emily.a362first;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static java.lang.String.valueOf;

public class AlarmClock extends AppCompatActivity { //implements AdapterView.OnItemSelectedListener {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmclock);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        this.context = this;

        //Initialize the Alarm Manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Initialize the time picker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        //Initialize the alarm text box
        update_text = (TextView) findViewById(R.id.update_text);

        // Create Calendar instance
        final Calendar calendar = Calendar.getInstance();

        //On click listeners for Start and Clear alarms
        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        Button alarm_off = (Button) findViewById(R.id.alarm_off);

        //Create intent for Alarm_Receiver to receive
        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);

        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            @TargetApi(Build.VERSION_CODES.M)
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                //Get integer values of time chosen by user
                int hour = alarm_timepicker.getHour();
                int minutes = alarm_timepicker.getMinute();

                //Convert ints to strings
                String minute_string = String.valueOf(minutes);
                String hour_string = String.valueOf(hour);
                String time_of_day = "AM";
                if (minutes < 10) {
                        minute_string = "0" + valueOf(minutes);
                }
                else {
                    minute_string = valueOf(minutes);
                }
                if (hour == 0) {
                    hour_string = valueOf(12);
                }

                if (hour > 12) {
                    time_of_day = "PM";
                    hour_string = valueOf(hour - 12);
                }

                set_alarm_text("Alarm set for " + hour_string + ":"
                        + minute_string + " " + time_of_day);

                my_intent.putExtra("extra", "alarm on");

                //Create pending intent to delay intent until user specifies time
                pending_intent = PendingIntent.getBroadcast(AlarmClock.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //Set Alarm Manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pending_intent);
            }
        });

        alarm_off.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                set_alarm_text("Alarm off");
                alarm_manager.cancel(pending_intent);

                my_intent.putExtra("extra", "alarm off");
                sendBroadcast(my_intent);
            }
        });
    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }

    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
