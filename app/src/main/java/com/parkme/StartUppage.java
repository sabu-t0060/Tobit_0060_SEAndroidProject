package com.parkme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class StartUppage extends AppCompatActivity {
    private  static int screen_out = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_start_uppage);

       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              Intent loginPage = new Intent(StartUppage.this,loginmenu.class);
              startActivity(loginPage);
              finish();
            }
        },screen_out);
    }
}
