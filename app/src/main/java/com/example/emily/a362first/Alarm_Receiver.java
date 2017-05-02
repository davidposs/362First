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

        /* Get extra strings from the intent */
        String get_your_string = intent.getExtras().getString("extra");
        Log.e("The key is: ", get_your_string);

        /* Integer as user input for song selection */
        Integer get_sound_choice = intent.getExtras().getInt("sound_choice");

        /* Intent to start the Ringtone Playing Service */
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        /*  Pass the extra string to the ringtone playing service */
        service_intent.putExtra("extra", get_your_string);

        /* Pass the user inputted song selection to Ringtone Playing Service */
        service_intent.putExtra("sound_choice", get_sound_choice);

        /* Start the Ringtone service */
        context.startService(service_intent);
    }
}
