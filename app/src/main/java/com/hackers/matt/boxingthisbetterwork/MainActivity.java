package com.hackers.matt.boxingthisbetterwork;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.widget.Toast;

import org.w3c.dom.Text;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public boolean paused = false;
    public boolean canceled = false;
    long milliLeft, min, sec;
    Button button, button2, button3;
    TextView timerText;
    LinearLayout layout;
    int secondsPassed = 0;
    private SoundPool soundPool;
    boolean plays = false, loaded = false;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;
    int counter;
    private int bobID;
    private int sliplID;
    private int sliprID;
    private int oneID;
    private int twoID;
    private int threeID;
    private int fourID;
    private int fiveID;
    private int sixID;
    private int loadCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.Start);
        button2 = (Button) findViewById(R.id.Pause);
        button3 = (Button) findViewById(R.id.Reset);
        timerText = (EditText) findViewById(R.id.countdownClock);
        layout = (LinearLayout) findViewById(R.id.mainLayout);


        int time = Integer.parseInt(timerText.getText().toString().split(":")[0]) * 60 * 1000 +
                Integer.parseInt(timerText.getText().toString().split(":")[1]) * 1000;
        Log.v("TIMER:", "" + time);
        final CountDownTimer ctd = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                milliLeft = millisUntilFinished - (secondsPassed * 1000);
                min = (millisUntilFinished / (1000 * 60));
                sec = (millisUntilFinished / 1000 - min * 60);
                if (sec < 10)
                    timerText.setText(Long.toString(min) + ":0" + Long.toString(sec));
                else
                    timerText.setText(Long.toString(min) + ":" + Long.toString(sec));
                secondsPassed++;
                playCombo();
                if (millisUntilFinished == 30 * 1000)
                    playWarning();
            }

            @Override
            public void onFinish() {
                timerText.setText("FINISH!!!!");
                layout.setBackgroundColor(ContextCompat.getColor(layout.getContext(), R.color.colorDone));
                timerText.setEnabled(true);
            }

        };
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ctd.start();
                View someView = findViewById(R.id.mainLayout);
                View root = someView.getRootView();
                layout.setBackgroundColor(ContextCompat.getColor(layout.getContext(), R.color.colorActive));
                timerText.setEnabled(false);
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ctd.cancel();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ctd.cancel();
                secondsPassed = 0;
            }
        });
    }

    public void playRest() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.timer_finished);
        mp.setVolume(0.05f, 0.05f);
        mp.start();
        System.out.print("rest");
    }

    public void playCombo() {


        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume =  maxVolume;
        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // the counter will help us recognize the stream id of the sound played  now
        int counter = 0;
        // Load the sounds
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        bobID = soundPool.load(this, R.raw.bob, 1);
        sliprID = soundPool.load(this, R.raw.slipr, 1);
        sliplID = soundPool.load(this, R.raw.slipl, 1);
        oneID = soundPool.load(this, R.raw.one, 1);
        twoID = soundPool.load(this, R.raw.two, 1);
        threeID = soundPool.load(this, R.raw.three, 1);
        fourID = soundPool.load(this, R.raw.four, 1);
        fiveID = soundPool.load(this, R.raw.five, 1);
        sixID = soundPool.load(this, R.raw.six, 1);

//        float duration = 0;
        Random rand = new Random();
        int numMoves = rand.nextInt(6);
        for (int i = 0; i < numMoves; i++) {

            int move = rand.nextInt(12);

            playSound(fourID);
//            switch (move) {
//                case 0:
//                    playSound(bobID);
//
//                    Log.v("VOICE: ", "BOB");
//                    break;
//                case 1:
//                    playSound(sliplID);
//                    Log.v("VOICE: ", "SLIPL");
//                    break;
//                case 2:
//                    playSound(sliprID);
//                    Log.v("VOICE: ", "SLIPR");
//                    break;
//                case 3:
//                    playSound(oneID);
//                    Log.v("VOICE: ", "ONE");
//                    break;
//                case 4:
//                    playSound(twoID);
//                    Log.v("VOICE: ", "TWO");
//                    break;
//                case 5:
//                    playSound(threeID);
//                    Log.v("VOICE: ", "THREE");
//                    break;
//                case 6:
//                    playSound(fourID);
//                    Log.v("VOICE: ", "FOUR");
//                    break;
//                case 7:
//                    playSound(fiveID);
//                    Log.v("VOICE: ", "FIVE");
//                    break;
//                case 8:
//                    playSound(sixID);
//                    Log.v("VOICE: ", "SIX");
//                    break;
//                default:
//                    break;
        }


    }
//        if(mp!=null) {
//            if(mp.isPlaying())
//                mp.stop();
//            mp.reset();
//            mp.release();
//            mp=null;
//        }
//  }

    public void playSound(int soundID) {
        // Is the sound loaded does it already play?
        Log.v("PLAYING SOUND", "IN SOUND METHOD");
        if (loaded && !plays) {
            soundPool.play(soundID, volume, volume, 1, 0, 1f);
            counter = counter++;
            Toast.makeText(this, "Played sound", Toast.LENGTH_SHORT).show();
            plays = true;
        }
        if (plays) {
            soundPool.stop(soundID);
            this.fourID = soundPool.load(this, R.raw.four, counter);
            plays = false;
        }

    }



    public void playWarning() {
        System.out.println("warning");
        MediaPlayer mp = MediaPlayer.create(this, R.raw.round_warning);
        mp.setVolume(0.05f, 0.05f);
        mp.start();
    }

}

