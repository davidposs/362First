package com.example.emily.a362first;

/**
 * Created by David on 4/18/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddReflection extends AppCompatActivity {
    public TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reflection);
        dateText = (TextView) findViewById(R.id.textView);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy", Locale.ENGLISH);
        String currentDate = sdf.format(date);
        dateText.setText(currentDate);
    }

    public void saveMessage(View v) {
        EditText text = (EditText) findViewById(R.id.enter_content);
        String message = text.getText().toString();
        String filename = "content.txt";

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy", Locale.ENGLISH);
        String currentDate = sdf.format(date);

        FileOutputStream fos;
        try {
            fos = openFileOutput(filename, Context.MODE_APPEND);
            fos.write(currentDate.getBytes());
            fos.write(message.getBytes());
            fos.close();
            Toast.makeText(this, "Reflection has been saved!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
