package com.precoders.adminpanelfinder;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * Created by Farsheel on 7/8/17.
 */
public class SplashScreen extends AppCompatActivity {

    public static long TIME=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
                finish();
            }
        },TIME);

    }
}
