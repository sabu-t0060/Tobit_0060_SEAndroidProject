package com.parkme;

import android.content.ContentValues;
import android.content.Intent;
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

public class usersignuptab extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.usersignup,container,false);
        final SQLiteDatabase pdb = ParkDbOperations.getWritable(v.getContext());
        final EditText name = (EditText) v.findViewById(R.id.admTxt);
        final EditText password = (EditText) v.findViewById(R.id.pswTxt);
        final EditText conpassword = (EditText) v.findViewById(R.id.conTxt);
        final EditText email = (EditText) v.findViewById(R.id.emailTxt);
        final EditText phone = (EditText) v.findViewById(R.id.phoneTxt);
        final Button btn = (Button) v.findViewById(R.id.loginBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyDB","onCreate");
          if(password.getText().toString().equals(conpassword.getText().toString())){
                    ContentValues values = new ContentValues();
                    /*ContentValues admVal = new ContentValues();
                    admVal.put(parkTbl.adminName,"admin");
                    admVal.put(parkTbl.adminPassword,"admin");
                    admVal.put(parkTbl.adminPhone,"123456789");
                    admVal.put(parkTbl.adminEmail,"admin@parkme.com");*/
                    user u = new user();
                    //Log.d("MyDB",name.getText().toString());
                    values.put(u.userName,name.getText().toString());
                    values.put(u.password, password.getText().toString());
                    values.put(u.email, email.getText().toString());
                    values.put(u.phone, phone.getText().toString());

                    //long newRowId =1;
                    long newRowId = pdb.insert("user", null, values);
                   //long newadmin = pdb.insert("admin",null,admVal);
                    if(newRowId ==-1){
                        Toast.makeText(v.getContext(),"Database Error",Toast.LENGTH_SHORT).show();
                        Log.d("MyDB","error");
                    }
                    else {
                        Toast.makeText(v.getContext(),"New user is added",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(),loginmenu.class);
                        startActivity(i);
                        Log.d("MyDB","success");
                    }

                }
             else {
              Toast.makeText(v.getContext(),"Please Confirm Password",Toast.LENGTH_SHORT).show();

          }



            }
        });
        return v;

    }


}
