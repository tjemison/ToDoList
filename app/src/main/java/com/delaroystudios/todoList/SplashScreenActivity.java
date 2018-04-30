package com.delaroystudios.todoList;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DOMINUS on 4/29/18.
 */

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIMER = 3000;
    public SplashScreenActivity(){}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //The code here executes once the specified time has passed. In our case,
                //once 3000 milliseconds are up, an intent to our main activity will be called and
                //we will finsh this activity to prevent the user from accessing it by pressing the back button.
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_SCREEN_TIMER);
    }


}
