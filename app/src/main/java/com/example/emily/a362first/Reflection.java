package com.example.emily.a362first;

/**
 * Created by David on 4/18/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reflection extends AppCompatActivity {

    public ArrayList<String> titles;
    public ArrayAdapter<String> titlesAdapter;
    public ListView lvTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);
        lvTitles = (ListView) findViewById(R.id.lvTitles);
        titles = new ArrayList<String>();
        //readTitles();
        titlesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        lvTitles.setAdapter(titlesAdapter);
    }

    private void readTitles() {
        try {
            File dir = getFilesDir();
            File reflection = new File(dir, "content.txt");
            Scanner in = new Scanner(reflection).useDelimiter("//s*@@@//s*");
            int index = 0;
            while (in.hasNext()) {
                titles.add(index, in.next());
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNew(View v) {
        Intent add = new Intent(this, AddReflection.class);
        startActivity(add);
    }
}

