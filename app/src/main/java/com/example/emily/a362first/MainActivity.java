package com.example.emily.a362first;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static android.hardware.Sensor.TYPE_STEP_COUNTER;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    NodeList nodelist;
    EditText title, link;
    boolean running = false;

    /* TextViews for step counter and Quote of the Day */
    TextView pedomSteps, QoTD;

    /* URL we fetch quote from */
    String URL = "https://www.quotesdaddy.com/feed/tagged/Inspirational";

    /* Sensors for the pedometer */
    SensorManager sensorManager;

    private static String getNode(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();
        Node nValue = nlList.item(0);
        return nValue.getNodeValue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Basic necessities */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Step Counter Initializations
        *  Code for Pedometer. Uncomment if you want to work on it.
        * */
        pedomSteps = (TextView) findViewById(R.id.stepcounter);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        //stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        /* Quote of the Day */
        QoTD = (TextView) findViewById(R.id.quote);

        new DownloadXML().execute(URL);

        /* Weather */
        FloatingActionButton btnWeather = (FloatingActionButton) findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://weather.com/weather/today/l/USCA0408:1:US"));
                startActivity(intent);
            }
        });
    }//end onCreate

    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //intentionally blank
    }

    public void openStopwatch(View view) {
        Intent intent = new Intent(this, Stopwatch.class);
        startActivity(intent);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            int steps = Math.round(event.values[0]);
            pedomSteps.setText(String.valueOf(steps));
        }
    }

    public void openToDO(View view) {
        Intent intent = new Intent(this, TDLIST.class);
        startActivity(intent);
    }

    public void openAlarmClock(View view) {
        Intent intent = new Intent(this, AlarmClock.class);
        startActivity(intent);
    }

    public void openDiary(View view) {
        Intent intent = new Intent(this, Diary.class);
        intent.putExtra("Main", "no");
        startActivityForResult(intent, 1);
    }

    public void openReflection(View view) {
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_CANCELED) {
                    //????????????????
                }
        }
    }

    public void openWeather(View view) {

    }

    /* Download the Quote of the Day and display it */
    private class DownloadXML extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... Url) {
            try {
                URL url = new URL(Url[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
                nodelist = doc.getElementsByTagName("item");
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            for (int temp = 0; temp < nodelist.getLength(); temp++) {
                Node nNode = nodelist.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // Set the texts into TextViews from item nodes
                    QoTD.setText(QoTD.getText() +
                            getNode("title", eElement) + "\n");
                }
            }
        }
    }//end DownloadXML
}
