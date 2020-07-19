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

public class userogintab extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.userlogin,container,false);
        final SQLiteDatabase pdb = ParkDbOperations.getWritable(v.getContext());
        final EditText name = (EditText) v.findViewById(R.id.admTxt);
        final EditText password = (EditText) v.findViewById(R.id.pswTxt);
        final Button login = (Button) v.findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String pswd = password.getText().toString();

                String selectQuery = "SELECT  * FROM user where userName = '"+userName+"' and password = '"+ pswd+ "';'";
                try {
                    Cursor cursor = pdb.rawQuery(selectQuery, null);
                    if(cursor != null){
                        cursor.moveToFirst();
                        if (cursor.getCount() > 0){
                            int t = cursor.getCount();
                            String tem = String.valueOf(t);
                            Log.d("su",tem);
                            Log.d("databse","success");
                            String s = cursor.getString(0);
                            Intent i = new Intent(getContext(),userHome.class);

                            i.putExtra("id",Integer.parseInt(s));
                            startActivity(i);

                        }
                        else{
                            int t = cursor.getCount();
                            String tem = String.valueOf(t);
                            Log.d("su",tem);
                            Log.d("databse","Error");
                        }
                    }
                    else {
                        int t = cursor.getCount();
                        String tem = String.valueOf(t);
                        Log.d("su",tem);
                        Log.d("databbase","Error select");
                    }
                }
                catch (Exception ex){

                }
            }
        });

            return v;
    }
}
