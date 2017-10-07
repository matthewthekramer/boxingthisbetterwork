package com.hackers.matt.boxingthisbetterwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public int counter,pausedCounter = 0;
    public boolean paused = false;
    public boolean canceled = false;
    long milliLeft,min,sec;
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
        final CountDownTimer ctd = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                milliLeft = millisUntilFinished;
                min = (milliLeft/(1000*60));
                sec = (milliLeft/1000 - min * 60);
                textView.setText(Long.toString(min)+ ":" + Long.toString(sec));
            }

            @Override
            public void onFinish() {
                textView.setText("FINISH!!!!");
            }

        };
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                ctd.start();
            }

        });
       /** Trying to get pause button to work...
        *
        *
        * button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                ctd.cancel();
                paused = true;
            }
        });
*/
        button3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ctd.cancel();
                milliLeft = 300000;
            }
        });
    }
}

