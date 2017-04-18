package com.example.emily.a362first;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* Created by David on 4/12/2017 */

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Received in receiver.. ", "Whew laddy");

        //fetch extra strings from the intent
        String get_your_string = intent.getExtras().getString("extra");
        Log.e("The key is: ", get_your_string);
        //Create intent to Ringtone service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        //pass the extra string from main activity to the ringtone playing service
        service_intent.putExtra("extra", get_your_string);

        //Start the Ringtone service
        context.startService(service_intent);
    }

}
