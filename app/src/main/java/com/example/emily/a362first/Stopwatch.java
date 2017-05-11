package com.example.emily.a362first;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stopwatch extends AppCompatActivity {

    TextView textView;
    Button btnStart, btnStop, btnRestart, btnLap;
    long time_in_milliseconds, start_time, time_buff, update_time;
    Handler handler;
    int seconds, minutes, milliseconds, counter;
    ListView listView;
    String[] lapElements = new String[]{};
    List<String> lapTimes;

    /* Lap function stuff */
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        textView = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnRestart = (Button) findViewById(R.id.btnRestart);
        btnLap = (Button) findViewById(R.id.btnLap);
        listView = (ListView) findViewById(R.id.listView);
        counter = 0;
        handler = new Handler();

        lapTimes =
                new ArrayList<String>(Arrays.asList(lapElements));

        adapter = new ArrayAdapter<String>(Stopwatch.this,
                R.layout.list_view_element, lapTimes);


        listView.setAdapter(adapter);
        //listViewTime.setAdapter(time_adapter);
        btnLap.setEnabled(false);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_time = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                btnRestart.setEnabled(false);
                btnLap.setEnabled(true);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_buff += time_in_milliseconds;
                handler.removeCallbacks(runnable);
                btnRestart.setEnabled(true);
                btnLap.setEnabled(false);
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_buff = 0L;
                start_time = 0L;
                time_in_milliseconds = 0L;
                update_time = 0L;
                counter = 0;
                minutes = 0;
                seconds = 0;
                milliseconds = 0;
                lapTimes.clear();
                btnRestart.setEnabled(true);
                btnLap.setEnabled(false);
                textView.setText("00:00:000");
                adapter.notifyDataSetChanged();
            }
        });


        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                android.util.Log.d("counter", counter + "");
                lapTimes.add(counter + ".  "
                        + textView.getText().toString());
                android.util.Log.d("after", textView.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
    public Runnable runnable = new Runnable() {
        public void run() {
            time_in_milliseconds = SystemClock.uptimeMillis() - start_time;
            update_time = time_buff + time_in_milliseconds;
            seconds = (int) (update_time / 1000);
            minutes = seconds / 60;
            seconds %= 60;
            milliseconds = (int) (update_time % 1000);
            textView.setText("" + minutes + ":" + String.format("%02d", seconds) + ":" +
                    String.format("%03d", milliseconds));
            handler.postDelayed(this, 0);
        }
    };
}