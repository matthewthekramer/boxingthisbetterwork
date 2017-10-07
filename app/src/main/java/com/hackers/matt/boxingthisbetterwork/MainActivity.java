package com.hackers.matt.boxingthisbetterwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import org.w3c.dom.Text;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public boolean paused = false;
    long milliLeft = 300000;
    int secondsPassed = 0 ;
    long min,sec;
    Button button,button2,button3;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.Start);
        button2 = (Button) findViewById(R.id.Pause);
        button3 = (Button) findViewById(R.id.Reset);
        textView = (TextView) findViewById(R.id.countdownClock);
        final CountDownTimer ctd = new CountDownTimer(65000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                milliLeft = millisUntilFinished - (secondsPassed * 1000);
                min = (millisUntilFinished/(1000*60));
                sec = (millisUntilFinished/1000 - min * 60);
                if(sec < 10)
                    textView.setText(Long.toString(min) + ":0"+  Long.toString(sec));
                else
                    textView.setText(Long.toString(min)+ ":" + Long.toString(sec));
                secondsPassed++;
                playCombo();
                if(millisUntilFinished == 30 * 1000)
                    playWarning();
            }

            @Override
            public void onFinish() {
                textView.setText("0:00");
                playRest();
            }

        };
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                ctd.start();
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                ctd.cancel();
            }
        });

        button3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ctd.cancel();
                secondsPassed = 0;
            }
        });
    }

    public void playRest(){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.timer_finished);
        mp.setVolume(0.05f, 0.05f);
        mp.start();
        System.out.print("rest");
    }

    public void playCombo() {
        MediaPlayer mp = null;
        Random rand = new Random();
        int numMoves = rand.nextInt(6);
        for (int i = 0; i < numMoves; i++) {
            int move = rand.nextInt(12);
            switch (move) {
                case 0:
                    mp = MediaPlayer.create(this, R.raw.bob);
                    mp.start();
                    mp = null;
                    break;
                case 1:
                    mp = MediaPlayer.create(this, R.raw.slipl);
                    mp.start();
                    break;
                case 2:
                    mp = MediaPlayer.create(this, R.raw.slipr);
                    mp.start();
                    break;
                case 3:
                    mp = MediaPlayer.create(this, R.raw.one);
                    mp.start();
                    break;
                case 4:
                    mp = MediaPlayer.create(this, R.raw.two);
                    mp.start();
                    break;
                case 5:
                    mp = MediaPlayer.create(this, R.raw.three);
                    mp.start();
                    break;
                case 6:
                    mp = MediaPlayer.create(this, R.raw.four);
                    mp.start();
                    break;
                case 7:
                    mp = MediaPlayer.create(this, R.raw.five);
                    mp.start();
                    break;
                case 8:
                    mp = MediaPlayer.create(this, R.raw.six);
                    mp.start();
                    break;
                default:
                    break;
            }
            mp = null;
        }
    }

    public void playWarning() {
        System.out.println("warning");
        MediaPlayer mp = MediaPlayer.create(this, R.raw.round_warning);
        mp.setVolume(0.05f, 0.05f);
        mp.start();
    }
}

