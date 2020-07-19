package com.parkme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ParkDbOperations {
    private static SQLiteDatabase pdb = null;
    private static ParkDb db = null;

    public static SQLiteDatabase getWritable(Context context) {
        if (db == null)
            db = new ParkDb(context);

        if (pdb == null)
            pdb = db.getWritableDatabase();

        return pdb;

    }
    public static int displayAll(TableLayout theView, SQLiteDatabase wdb, Context context, String selectQuery) {

        theView.removeAllViews();
        try {
            Cursor cursor = wdb.rawQuery(selectQuery, null); // 2nd arg is for where clause. For joining tables, don't use it

            String[] columnNames = cursor.getColumnNames();

            // looping through all rows and adding to list
            if (cursor != null) {
                cursor.moveToFirst();
                TextView data;
                TableRow row;

                // create the header
                row = new TableRow(context);
                for (int i=0 ; i < columnNames.length; i++) {
                    row.setPadding(2,2,2,2);
                    data = new TextView(context);

                    data.setTypeface(Typeface.DEFAULT_BOLD);
                    data.setText(columnNames[i]);
                    data.setTextColor(0xFF000000);
                    row.addView(data);
                }
                theView.addView(row);

                int cnt = 0 ;
                do {
                    row = new TableRow(context);      // creates 1 row at a time
                    // left, top, right, bottom
                    row.setPadding(2,2,2,2);

                    for (int x = 0; x < cursor.getColumnCount(); x++) {
                        data = new TextView(context);  // creates TextView dynamically
                        if (x == 0) {                           // variable x represents the column, x == 0 means first column
                            data.setTypeface(Typeface.DEFAULT_BOLD);
                            data.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        data.setText(cursor.getString(x));
                        data.setTextColor(Color.rgb(252, 61, 3));
                        row.addView(data);                      // adds each column to the row
                    }
                    theView.addView(row);                       // adds the new row to the table
                } while (cursor.moveToNext());

                theView.setStretchAllColumns(true);
                cursor.close();
                return 2;
            }
        } catch (Exception ex) {
            return 1;
        }
        return 0;
    }


}
