package com.example.emily.a362first;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/* Created by David on 4/12/2017 */

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("In the service", "startCommand" + startId + ": " + intent);
        //Toast.makeText(this, "should start", Toast.LENGTH_LONG).show();
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state extra is", state);

        //Converts extra strings from Intent to startIds (0 or 1)
        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (!this.isRunning && startId == 1) {
            //Create instance of media player
            Log.e("Nothing playing, ", "lets play something");
            media_song = MediaPlayer.create(this, R.raw.springish);
            //Start ringtone
            media_song.start();
            this.isRunning = true;
            this.startId = 0;
        }
        else if (this.isRunning && startId == 0) {
            //Stop ringtine
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startId = 0;
        }
        else if(!this.isRunning && startId == 0) {
            this.isRunning = false;
            this.startId = 0;
        }
        else if(this.isRunning && startId == 1) {
            this.isRunning = true;
            this.startId = 1;
        }
        else {
            Log.e("How did you", " even reach this?");
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
