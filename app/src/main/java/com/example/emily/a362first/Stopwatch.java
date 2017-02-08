package com.example.emily.a362first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Stopwatch extends AppCompatActivity {

    TextView textView;
    Button btnStart, btnStop, btnRestart, btnLap;
    long time_in_milliseconds, start_time, time_buff, update_time;
    Handler handler;
    int seconds, minutes, milliseconds, counter;
    ListView listView;
    String[] ListElements = new String[] {};
    List<String> ListElementsArrayList;
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

        ListElementsArrayList =
                new ArrayList<String>(Arrays.asList(ListElements));
        adapter = new ArrayAdapter<String>(Stopwatch.this,
                android.R.layout.simple_list_item_1,
                ListElementsArrayList);

        listView.setAdapter(adapter);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_time = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                btnRestart.setEnabled(false);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_buff += time_in_milliseconds;
                handler.removeCallbacks(runnable);
                btnRestart.setEnabled(true);
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
                ListElementsArrayList.clear();
                btnRestart.setEnabled(true);
                textView.setText("00:00:000");
                adapter.notifyDataSetChanged();
            }
        });

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                ListElementsArrayList.add(String.valueOf(counter) + ". "
                        + textView.getText().toString());
            }
        });
    }
        public Runnable runnable = new Runnable() {
            public void run() {
                time_in_milliseconds = SystemClock.uptimeMillis() - start_time;
                update_time = time_buff + time_in_milliseconds;
                seconds = (int)(update_time/ 1000);
                minutes = seconds / 60;
                seconds %= 60;
                milliseconds = (int)(update_time % 1000);

                textView.setText("" + minutes + ":" + String.format("%02d", seconds) + ":" +
                    String.format("%03d", milliseconds));

                handler.postDelayed(this, 0);
            }
        };
}
