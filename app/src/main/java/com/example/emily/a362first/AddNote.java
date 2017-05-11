package com.example.emily.a362first;

/**
 * Created by David on 4/18/2017.
 */


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class AddNote extends AppCompatActivity {
    private String delim = "@@@";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void saveMessage(View v) {
        EditText text1 = (EditText) findViewById(R.id.enter_title);
        String title = text1.getText().toString();
        EditText text2 = (EditText) findViewById(R.id.enter_content);
        String message = text2.getText().toString();

        String titleFile = "title.txt", messageFile = "message.txt";

        FileOutputStream fos1;
        FileOutputStream fos2;
        try {
            fos1 = openFileOutput(titleFile, Context.MODE_APPEND);
            fos1.write(title.getBytes());
            fos1.write(delim.getBytes());
            fos1.close();
            fos2 = openFileOutput(messageFile, Context.MODE_APPEND);
            fos2.write(message.getBytes());
            fos2.write(delim.getBytes());
            fos2.close();
            Toast.makeText(this, "Note has been saved!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
