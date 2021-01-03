package com.polimigo.babydaycare.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.polimigo.babydaycare.R;
import maes.tech.intentanim.CustomIntent;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){

            Thread timer =new Thread(){
                public void run(){
                    try {
                        sleep(2000);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                            startActivity(i);
                            finish();
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            };
            timer.start();
        }
        else{

        }
    }
}