package com.parkme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class userHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        Intent i = getIntent();
        int id = i.getIntExtra("id",9);
        Log.d("id",String.valueOf(id));

        ImageView loc = (ImageView) findViewById(R.id.bookBtn);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userHome.this,booking.class);
                startActivity(i);

            }
        });

    }
}
