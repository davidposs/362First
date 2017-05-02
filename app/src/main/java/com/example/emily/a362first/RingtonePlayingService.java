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

import java.util.Random;


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

        /* Create intent for this process */
        Intent intent_alarm_clock = new Intent(
                this.getApplicationContext(),
                AlarmClock.class);

        /* Create a pending intent for the alarm clock */
        PendingIntent pending_alarm_clock = PendingIntent.getActivity(this, 0, intent_alarm_clock, 0);

        /* Create the notification the user will see */
        NotificationManager notify_manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        /* Values to store user inputs */
        Integer sound_choice = intent.getExtras().getInt("sound_choice");
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state extra is", state);

        Notification notification_popup = new Notification.Builder(this)
                .setContentTitle("Lifestyle Alarm")
                .setContentText("Click to stop")
                .setSmallIcon(R.drawable.alarmclock)
                .setContentIntent(pending_alarm_clock)
                .setAutoCancel(true)
                .build();

        /* Converts extra strings from Intent to startIds (0 or 1) */
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
            /* Create instance of media player */
            Log.e("Nothing playing, ", "lets play something");

            this.isRunning = true;
            this.startId = 0;
            notify_manager.notify(0, notification_popup);

            if (sound_choice == 0) {
                int min = 1;
                int max = 8;
                Random rNumber = new Random();
                int song_number = rNumber.nextInt(min + max);
                Log.e("Random number is: ", String.valueOf(song_number));

                switch(song_number) { /* Randomly generated song number */
                    case 1:
                        media_song = MediaPlayer.create(this, R.raw.broke_for_free);
                        media_song.start();
                        break;
                    case 2:
                        media_song = MediaPlayer.create(this, R.raw.ghost_dance);
                        media_song.start();
                        break;
                    case 3:
                        media_song = MediaPlayer.create(this, R.raw.i_love_you);
                        media_song.start();
                        break;
                    case 4:
                        media_song = MediaPlayer.create(this, R.raw.last_dance);
                        media_song.start();
                        break;
                    case 5:
                        media_song = MediaPlayer.create(this, R.raw.ragtime_dance);
                        media_song.start();
                        break;
                    case 6:
                        media_song = MediaPlayer.create(this, R.raw.requiem_for_a_fish);
                        media_song.start();
                        break;
                    case 7:
                        media_song = MediaPlayer.create(this, R.raw.springish);
                        media_song.start();
                        break;
                    case 8:
                        media_song = MediaPlayer.create(this, R.raw.take_me_higher);
                        media_song.start();
                        break;
                }

            }
            else { /* User entered a value for their song */
                switch (sound_choice) {
                    case 1:
                        media_song = MediaPlayer.create(this, R.raw.broke_for_free);
                        media_song.start();
                        break;
                    case 2:
                        media_song = MediaPlayer.create(this, R.raw.ghost_dance);
                        media_song.start();
                        break;
                    case 3:
                        media_song = MediaPlayer.create(this, R.raw.i_love_you);
                        media_song.start();
                        break;
                    case 4:
                        media_song = MediaPlayer.create(this, R.raw.last_dance);
                        media_song.start();
                        break;
                    case 5:
                        media_song = MediaPlayer.create(this, R.raw.ragtime_dance);
                        media_song.start();
                        break;
                    case 6:
                        media_song = MediaPlayer.create(this, R.raw.requiem_for_a_fish);
                        media_song.start();
                        break;
                    case 7:
                        media_song = MediaPlayer.create(this, R.raw.springish);
                        media_song.start();
                        break;
                    case 8:
                        media_song = MediaPlayer.create(this, R.raw.take_me_higher);
                        media_song.start();
                        break;
                } /* end switch */
            } /* end else */
        } /* end if */

        /* Music is playing and we want to stop */
        else if (this.isRunning && startId == 0) {
            /* Stop ringtone */
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startId = 0;
        }

        /* Music is not playing, but user pressed alarm off
         * Do nothing */
        else if (!this.isRunning && startId == 0) {
            this.isRunning = false;
            this.startId = 0;
        }

        /* Music is playing, and user pressed alarm on anyway
        *  Do nothing */
        else if (this.isRunning && startId == 1) {
            this.isRunning = true;
            this.startId = 1;
        }

        /* Any other case */
        else {
            Log.e("How did you", " even reach this?");
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("Destroying.. ", ".");
        super.onDestroy();
        this.isRunning = false;
    }
}
