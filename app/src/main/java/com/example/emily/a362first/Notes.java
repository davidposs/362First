package com.example.emily.a362first;

/**
 * Created by David on 5/4/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Notes extends AppCompatActivity {

    public ArrayList<String> titles;
    public ArrayAdapter<String> titlesAdapter;
    public ListView lvTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        lvTitles = (ListView) findViewById(R.id.lvTitles);
        titles = new ArrayList<String>();
        readTitles();
        titlesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        lvTitles.setAdapter(titlesAdapter);
        setupLVListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        titles.clear();
        readTitles();
        titlesAdapter.notifyDataSetChanged();
    }

    private void setupLVListener() {
        lvTitles.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                        viewPrev(v, position);
                    }
                }
        );
    }

    private void readTitles() {
        try {
            File dir = getFilesDir();
            File reflection = new File(dir, "title.txt");
            Scanner in = new Scanner(reflection);
            in.useDelimiter("@@@");
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
        Intent intent = new Intent(this, AddNote.class);
        startActivity(intent);
    }

    public void viewPrev(View v, int position) {
        Intent intent = new Intent(this, ViewNote.class);
        intent.putExtra("index", position);
        startActivity(intent);
    }
}

