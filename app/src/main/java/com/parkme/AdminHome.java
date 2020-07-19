package com.parkme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        final Button log = (Button) findViewById(R.id.logout) ;
        final ImageView pro = (ImageView) findViewById(R.id.profile);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (AdminHome.this,loginmenu.class);
                startActivity(i);
            }
        });
        Intent i = getIntent();
       final int id = i.getIntExtra("id",9);
        Log.d("id",String.valueOf(id));


        ImageView loc = (ImageView) findViewById(R.id.location);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHome.this,locationMenu.class);
                startActivity(i);

            }
        });
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(AdminHome.this,profile.class);
                startActivity(in);
                in.putExtra("id",id);
            }
        });




    }
}
