package com.example.car_agency;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Splash_Screen extends AppCompatActivity {

    private GifImageView gifImageView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        gifImageView = (GifImageView)findViewById(R.id.gifImageView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(progressBar.VISIBLE);

        //Set GIFImageView resource
        try{
            InputStream inputStream = getAssets().open("cargif.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException ex)
        {

        }

        //Wait for 5 seconds and start Activity Main
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Splash_Screen.this.startActivity(new Intent(Splash_Screen.this,LoginFragment.class));
                Splash_Screen.this.finish();
            }
        },3000);
    }
        }

        /*Thread thread=new Thread(){
            @Override
            public void run(){
                try {
                    sleep(4000);
                    Intent russplash=new Intent(getApplicationContext(),SecondActivity.class);
                    startActivities(new Intent[]{russplash});
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}};
        thread.start();
    }*/

