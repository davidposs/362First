package com.example.emily.a362first;

/**
 * Created by David on 4/13/2017.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Diary extends AppCompatActivity {

    // static ListView lv;
    static ArrayList<Log> log = new ArrayList<Log>();
    static ArrayList<TextView> t = new ArrayList<TextView>();

    static ArrayList<String> arrayList;// = new ArrayList<String>();
    static ArrayAdapter<String> adapter;

    static ArrayList<Integer> arrayList2;
    static ArrayAdapter<Integer> adapter2;


    static int i = 0;
    static boolean test = false;
    static String food2;
    static int cal2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        /*if(test == false)
        {
            ListView lv = (ListView) findViewById(R.id.listView);
            arrayList = new ArrayList<String>();

            ListView lv2 = (ListView) findViewById(R.id.ListCal);
            arrayList2 = new ArrayList<Integer>();


            adapter = new ArrayAdapter<String>(Diary.this, android.R.layout.simple_list_item_1, arrayList);
            lv.setAdapter(adapter);

            adapter2 = new ArrayAdapter<Integer>(Diary.this, android.R.layout.simple_list_item_1, arrayList2);
            lv2.setAdapter(adapter2);

            test = true;
        }*/
    /*}

    //@Override
    public void OnResume(Bundle savedInstanceState)
    {
        super.onResume();*/
        Intent intent = getIntent();
        String main = intent.getStringExtra("Main");

        //if(test == false)
        //{
        ListView lv = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();

        ListView lv2 = (ListView) findViewById(R.id.ListCal);
        arrayList2 = new ArrayList<Integer>();


        adapter = new ArrayAdapter<String>(Diary.this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(adapter);

        adapter2 = new ArrayAdapter<Integer>(Diary.this, android.R.layout.simple_list_item_1, arrayList2);
        lv2.setAdapter(adapter2);

        test = true;
        //}

        //test = true;

        /*ListView lv = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();

        ListView lv2 = (ListView) findViewById(R.id.ListCal);
        arrayList2 = new ArrayList<Integer>();


        adapter = new ArrayAdapter<String>(Diary.this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(adapter);

        adapter2 = new ArrayAdapter<Integer>(Diary.this, android.R.layout.simple_list_item_1, arrayList2);
        lv2.setAdapter(adapter2);*/

        if(main.equals("yes"))// "yes");// "yes")) {
        {
            /*if(test == false) {
                test = true;
                ListView lv = (ListView) findViewById(R.id.listView);
                arrayList = new ArrayList<String>();

                adapter = new ArrayAdapter<String>(Diary.this, android.R.layout.simple_list_item_1, arrayList);
                lv.setAdapter(adapter);
                Toast toast = Toast.makeText(getApplicationContext(), "Value: " + test, Toast.LENGTH_SHORT);
                toast.show();
            }*/

            //lv.setAdapter(adapter);

            String foodName = intent.getStringExtra("Food");
            int Calories = intent.getIntExtra("Calories", 0);
            int count = intent.getIntExtra("Count", 0);

            Log newLog = new Log(foodName, Calories);

            Toast toast = Toast.makeText(getApplicationContext(), "The values are: " + newLog.getFood() + " " + newLog.getCal() + " " + newLog.getLogs(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void Back(View view)
    {
        Intent intent = new Intent(this, MainActivity.class); //changed from home
        setResult(Diary.RESULT_CANCELED, intent);
        finish();
    }


    public void AddItem(View view)
    {
        Intent intent = new Intent(this, FoodEntry.class);
        startActivityForResult(intent, 1); //1 is the request code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    food2 = FoodEntry.getString(data);
                    cal2 = FoodEntry.getInt(data);

                    arrayList.add(food2);
                    adapter.notifyDataSetChanged();


                    arrayList2.add(cal2);
                    adapter2.notifyDataSetChanged();
                }
                if(resultCode == Activity.RESULT_CANCELED)
                {

                }
        }
    }
    public void AddList(String food, int Cal)
    {

        //lv = (ListView)findViewById(R.id.listView);
        //arrayList = new ArrayList<String>();
        //adapter = new ArrayAdapter<String>(Diary.this, android.R.layout.simple_list_item_1, arrayList);
        //lv.setAdapter(adapter);
        //arrayList.add(food);
        //adapter.notifyDataSetChanged();

    }
}
