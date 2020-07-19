package com.parkme;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parkme.ui.main.SectionsPagerAdapter;

public class loginmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmenu);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

      //  final LayoutInflater factory = getLayoutInflater();
        //final View logView = factory.inflate(R.layout.usersignup, null);

       // Button btn = (Button) viewPager.findViewById(R.id.loginBtn);
      //  btn.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
       //         Log.d("MyDB","error");
                //Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();
         //   }
        //});

        /*
        final SQLiteDatabase pdb = ParkDbOperations.getWritable(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.admTxt);
                EditText password = (EditText) findViewById(R.id.pswTxt);
                EditText email = (EditText) findViewById(R.id.emailTxt);
                EditText phone = (EditText) findViewById(R.id.phoneTxt);
                name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(loginmenu.this,"added",Toast.LENGTH_SHORT).show();
                        Log.d("MyDB","error");
                    }
                });
                ContentValues values = new ContentValues();
                user u = new user();
                values.put(u.userName, name.getText().toString());
                values.put(u.password, password.getText().toString());
                values.put(u.email, email.getText().toString());
                values.put(u.phone, phone.getText().toString());

                long newRowId = pdb.insert("user", null, values);
                if(newRowId ==-1){
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    Log.d("MyDB","error");
                }
                else {
                    Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();
                    Log.d("MyDB","success");
                }
            }
        });*/

    }
}