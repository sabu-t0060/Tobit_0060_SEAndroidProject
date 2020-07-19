package com.parkme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class booking extends AppCompatActivity {
    SQLiteDatabase pdb ;
    int locid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        pdb = ParkDbOperations.getWritable(this);
        ArrayList<String> deptIds = getlocation() ;
        final Spinner s = (Spinner) findViewById(R.id.loc);
        final Spinner t = (Spinner) findViewById(R.id.type);
        final CalendarView c = (CalendarView) findViewById(R.id.calendarView);
        final Button check = (Button) findViewById(R.id.check);
        final Button book = (Button) findViewById(R.id.book);

        c.setMinDate(System.currentTimeMillis() - 1000);
        c.setBackgroundColor(Color.rgb(168, 95, 50));
        t.setBackgroundColor(Color.rgb(168, 95, 50));
        // Context, layout , source of the data
        s.setBackgroundColor(Color.rgb(168, 95, 50));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, deptIds);
        s.setAdapter(adapter);
        s.setSelection(1);
        t.setSelection(1);
        s.setBackgroundColor(Color.rgb(168, 95, 50));



        final Calendar calendar = Calendar.getInstance();
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
             calendar.set(year,month,dayOfMonth);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 00:00:00");
                final TextView tv = (TextView) findViewById(R.id.textView);

                long milliTime = calendar.getTimeInMillis();
                Date date = new Date(milliTime);
                String ds = String.valueOf(date);
                Log.d("ds",ds);
                String location = s.getSelectedItem().toString();
                String type = t.getSelectedItem().toString();
                int f=0;
                if(type.equals("Per Day"))
                {
                   f=0;
                }else{
                   f=1;
                }
                String losel = "SELECT  locationid FROM location where locaname = '"+location+"'";

                Cursor cu = pdb.rawQuery(losel, null);
                if(cu != null){
                    cu.moveToFirst();
                    locid = cu.getInt(0);
                    Log.d("locid",String.valueOf(locid));
                }

               // String selectQuery = "SELECT  slotid FROM slot where slotid != (select slotid from slotrecord where dayfill = 0 and hourfill =0 and location = "+locid+");";

                String selectQuery = "SELECT  slotid FROM slot;";
                Cursor cursor = pdb.rawQuery(selectQuery, null);
                if(cursor != null){
                    cursor.moveToFirst();
                    if(cursor.getCount()==0){
                        locid = cursor.getInt(0);
                        Log.d("locid",String.valueOf(locid));
                        tv.setText("Booking Available");

                    }
                    else{
                        locid = cursor.getInt(0);
                        Log.d("locid",String.valueOf(locid));
                        tv.setText("Booking Available");
                        book.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(booking.this,userHome.class);

                                 startActivity(i);
                            }
                        });




                    }

                }

                Log.d("spin",String.valueOf(formatter.format(milliTime)));


            }
        });



    }
    private ArrayList<String> getlocation() {
        ArrayList<String> ids = new ArrayList<>();
        try {
            String selectQuery = "SELECT  locaname FROM location ";
            Cursor cursor = pdb.rawQuery(selectQuery, null); // 2nd arg is for where clause
            Log.d("spin",String.valueOf(cursor.getColumnCount()));
            int size = cursor.getCount(); // gets the number of rows

            int i = 0 ;
            while (cursor.moveToNext()){
                ids.add(cursor.getString(0));
                Log.d("spin",cursor.getString(0));
            }



            cursor.close();
        } catch (Exception ex) {
            Log.d("spin","errr");
        }

        return ids;}
    }

