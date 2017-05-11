package com.example.emily.a362first;

/**
 * Created by David on 5/4/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewNote extends AppCompatActivity {

    public ArrayList<String> titleList;
    public ArrayList<String> contentList;
    public TextView tvTitle;
    public TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        Intent intent = getIntent();
        int index = intent.getExtras().getInt("index");

        titleList = new ArrayList<String>();
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setTextSize(20);
        tvTitle.setText(retText(index, "title.txt", titleList));

        contentList = new ArrayList<String>();
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvContent.setTextSize(15);
        tvContent.setText(retText(index, "message.txt", contentList));
        tvContent.setMovementMethod(new ScrollingMovementMethod());
    }

    public String retText(int index, String name, ArrayList<String> array) {
        try {
            File dir = getFilesDir();
            File reflection = new File(dir, name);
            Scanner in = new Scanner(reflection);
            in.useDelimiter("@@@");
            int i = 0;
            while (in.hasNext()) {
                array.add(i++, in.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = array.get(index);
        return text;
    }

}
