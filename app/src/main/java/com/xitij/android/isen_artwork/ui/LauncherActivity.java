package com.xitij.android.isen_artwork.ui;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xitij.android.isen_artwork.R;


public class LauncherActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new CountDownTimer(1500,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

}
