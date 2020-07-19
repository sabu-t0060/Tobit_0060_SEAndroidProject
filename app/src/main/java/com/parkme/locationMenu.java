package com.parkme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class locationMenu extends AppCompatActivity {
    SQLiteDatabase pdb;
    TableLayout theView;
    LinearLayout li;
    String c1;
    double pcost;
    String c2;
    double hcost;
    int n;
    int nf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_menu);

        pdb = ParkDbOperations.getWritable(this);

       final Button addBtn = (Button) findViewById(R.id.addBtn);
        final Button edtBtn = (Button) findViewById(R.id.editBtn);
        final Button dtBtn = (Button) findViewById(R.id.delBtn);
        final EditText lname = (EditText)findViewById(R.id.locNamTxt);
       final  EditText pdcost = (EditText)findViewById(R.id.perDayCost);
        final EditText hlcost = (EditText)findViewById(R.id.hrlCostTxt);
        final EditText ns = (EditText)findViewById(R.id.noSlotsTxt);
        final EditText nfs = (EditText)findViewById(R.id.nofrSlotsTxt);
        final Button okBtn = (Button) findViewById(R.id.okbtn);
        final Button edt = (Button) findViewById(R.id.edit);
        final Button back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(locationMenu.this,AdminHome.class);
                startActivity(i);
            }
        });


         theView = (TableLayout) findViewById(R.id.locationTbl);
          li = (LinearLayout) findViewById(R.id.lay);
          li.setVisibility(View.INVISIBLE);
          okBtn.setVisibility(View.GONE);
        final String selectQuery = "SELECT * FROM location ";
       int l= ParkDbOperations.displayAll(theView,pdb,this,selectQuery);
        addBtn.setEnabled(true);
        edtBtn.setEnabled(true);
        dtBtn.setEnabled(true);
       TableLayout tr = (TableLayout) findViewById(R.id.locationTbl);

        Log.d("adminMnu",String.valueOf(l));
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                li.setVisibility(LinearLayout.GONE);
                okBtn.setVisibility(View.GONE);

                String c1 = pdcost.getText().toString();
                 pcost = Double.parseDouble(c1);
                String c2 = hlcost.getText().toString();
                 hcost = Double.parseDouble(c2);
                 n = Integer.parseInt(ns.getText().toString());
                 nf = Integer.parseInt(nfs.getText().toString());

                ContentValues values = new ContentValues();

                values.put("locaname",lname.getText().toString() );
                values.put("perdaycost", pcost);
                values.put("hourlycost", hcost);
                values.put("noofslots", n);
                values.put("freeslots", nf);


                long newRowId = pdb.insert("location", null, values);
                if(newRowId == -1){
                    Log.d("adminMnu","error");

                }
                else{
                     for(int i =0;i<n;i++){
                         ContentValues c = new ContentValues();
                         c.put("fill",0);
                         c.put("location",newRowId);
                         long nwsl = pdb.insert("slot",null,c);
                     }
                    int l= ParkDbOperations.displayAll(theView,pdb,locationMenu.this,selectQuery);
                    Log.d("adminMnu","success");
                    Intent i = new Intent(locationMenu.this,locationMenu.class);
                    startActivity(i);
                }

            }
        });
        edtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) findViewById(R.id.edit);
                li.setVisibility(View.VISIBLE);
                addBtn.setEnabled(true);
                edtBtn.setEnabled(true);
                dtBtn.setEnabled(true);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText lid = (EditText) findViewById(R.id.locid);
                        final String id = lid.getText().toString();

                        String sel = "select * from location where locationid = "+id+";";
                        Cursor cursor = pdb.rawQuery(sel, null);

                        if(cursor!= null){
                            cursor.moveToFirst();
                            if(cursor.getCount()>0){
                                okBtn.setVisibility(View.VISIBLE);
                                String s= cursor.getString(1);
                                lname.setText(s);
                                pdcost.setText(String.valueOf(cursor.getDouble(2)));
                                hlcost.setText(String.valueOf(cursor.getDouble(3)));
                                ns.setText(String.valueOf(cursor.getInt(4)));
                                nfs.setText(String.valueOf(cursor.getInt(5)));

                                okBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ContentValues values = new ContentValues();
                                        String c1 = pdcost.getText().toString();
                                        pcost = Double.parseDouble(c1);
                                        String c2 = hlcost.getText().toString();
                                        hcost = Double.parseDouble(c2);
                                        n = Integer.parseInt(ns.getText().toString());
                                        nf = Integer.parseInt(nfs.getText().toString());
                                        values.put("locaname",lname.getText().toString() );
                                        values.put("perdaycost", pcost);
                                        values.put("hourlycost", hcost);
                                        values.put("noofslots", n);
                                        values.put("freeslots", nf);
                                        Intent i = new Intent(locationMenu.this,locationMenu.class);

                                       long n= pdb.update("location",values,"locationid = "+id,null);
                                       if(n==-1){

                                           Log.d("update","error");
                                       }
                                       else{


                                           Log.d("update","success");
                                           int l= ParkDbOperations.displayAll(theView,pdb,locationMenu.this,selectQuery);
                                           startActivity(i);
                                       }
                                    }
                                });

                            }else{
                                Toast.makeText(v.getContext(),"Database Error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });




                Log.d("adminMnu","success");
            }
        });
        dtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                li.setVisibility(View.VISIBLE);
                Button b = (Button) findViewById(R.id.edit);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText lid = (EditText) findViewById(R.id.locid);
                        final String id = lid.getText().toString();

                        String sel = "select * from location where locationid = "+id+";";
                        Cursor cursor = pdb.rawQuery(sel, null);
                        if(cursor!= null){
                            cursor.moveToFirst();
                            if(cursor.getCount()>0){
                                okBtn.setVisibility(View.VISIBLE);
                                String s= cursor.getString(1);
                                lname.setText(s);
                                pdcost.setText(String.valueOf(cursor.getDouble(2)));
                                hlcost.setText(String.valueOf(cursor.getDouble(3)));
                                ns.setText(String.valueOf(cursor.getInt(4)));
                                nfs.setText(String.valueOf(cursor.getInt(5)));

                                okBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        pdb.delete("location", "locationid = "+id, null);
                                        Toast.makeText(v.getContext(),"Delete Successfull",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(locationMenu.this,locationMenu.class);
                                        startActivity(i);
                                    }
                                });

                            }else{
                                Toast.makeText(v.getContext(),"Database Error",Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                });


            }
        });

    }
}
