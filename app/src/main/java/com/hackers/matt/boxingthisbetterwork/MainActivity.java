package com.hackers.matt.boxingthisbetterwork;

import android.content.Context;
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
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public int counter,pausedCounter = 0;
    public boolean paused = false;
    public boolean canceled = false;
    long milliLeft,min,sec;
    Button button,button2,button3;
    TextView timerText;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.Start);
        button2 = (Button) findViewById(R.id.Pause);
        button3 = (Button) findViewById(R.id.Reset);
        timerText = (EditText) findViewById(R.id.countdownClock);
        layout = (LinearLayout) findViewById(R.id.mainLayout);

        final CountDownTimer ctd = new CountDownTimer(100000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                milliLeft = millisUntilFinished;
                min = (milliLeft/(1000*60));
                sec = (milliLeft/1000 - min * 60);
                timerText.setText(Long.toString(min)+ ":" + Long.toString(sec));
            }

            @Override
            public void onFinish() {
                timerText.setText("FINISH!!!!");
                layout.setBackgroundColor(ContextCompat.getColor(layout.getContext(), R.color.colorDone));
                timerText.setEnabled(true);
            }

        };
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                ctd.start();
                View someView = findViewById(R.id.mainLayout);
                View root = someView.getRootView();
                layout.setBackgroundColor(ContextCompat.getColor(layout.getContext(), R.color.colorActive));
                timerText.setEnabled(false);
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

