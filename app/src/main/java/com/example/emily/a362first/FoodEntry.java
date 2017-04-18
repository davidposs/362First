package com.example.emily.a362first;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;
        import java.util.ArrayList;

public class FoodEntry extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_entry);

        Intent intent = getIntent();
    }

    public void SaveData(View view) {
        EditText Food = (EditText) findViewById(R.id.editText);
        String food = Food.getText().toString();

        EditText Cal = (EditText) findViewById(R.id.editText2);
        String cal = Cal.getText().toString();
        int calories = Integer.parseInt(cal);

        //Toast toast = Toast.makeText(getApplicationContext(), "The values are: " + newLog.getFood() + " " + newLog.getCal() + " " + newLog.getLogs(), Toast.LENGTH_LONG);
        //toast.show();
        int num = 1;

        Intent intent = new Intent(this, Diary.class);
        intent.putExtra("Main", "yes");
        intent.putExtra("Food", food);
        intent.putExtra("Calories", calories);
        intent.putExtra("Count", num);
        setResult(Diary.RESULT_OK, intent);
        finish();
        //startActivity(intent);
        num++;
    }

    public void Cancel(View view) {
        Intent intent = new Intent(this, Diary.class);
        setResult(Diary.RESULT_CANCELED, intent);
        finish();
    }

    public static String getString(Intent intent) {
        return intent.getStringExtra("Food");

    }
    public static int getInt(Intent intent) {
        return intent.getIntExtra("Calories", 0);
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (MY_CHILD_ACTIVITY) : {
                if (resultCode == Diary.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("some_key");
                }
                break;
            }
        }
    }*/
}