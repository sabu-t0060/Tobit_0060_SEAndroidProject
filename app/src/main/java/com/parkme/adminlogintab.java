package com.parkme;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class adminlogintab extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.admin,container,false);
        final SQLiteDatabase pdb = ParkDbOperations.getWritable(v.getContext());
        final EditText name = (EditText) v.findViewById(R.id.admTxt);
        final EditText password = (EditText) v.findViewById(R.id.pswTxt);
        final Button login = (Button) v.findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String pswd = password.getText().toString();

                String selectQuery = "SELECT  * FROM admin where userName = '"+userName+"' and password = '"+ pswd+ "';";
                try {
                    Cursor cursor = pdb.rawQuery(selectQuery, null);
                    if(cursor != null){
                        cursor.moveToFirst();
                        if (cursor.getCount()>0){
                            Toast.makeText(v.getContext(),"Admin Entered",Toast.LENGTH_SHORT).show();
                            Log.d("su","success");
                            String s= cursor.getString(0);
                            Intent i = new Intent(getContext(),AdminHome.class);
                            i.putExtra("id",Integer.parseInt(s));
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(v.getContext(),"Error in Admin Name or Password",Toast.LENGTH_SHORT).show();
                            Log.d("su","Error");
                        }
                    }
                    else {
                        Log.d("database","Error select");
                    }
                }
                catch (Exception ex){

                }
            }
        });

        return v ;
    }
}
