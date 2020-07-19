package com.parkme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ParkDb extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Park.db";
    private static final String createUserTbl =
            "CREATE TABLE  user ("+ user.user_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    user.userName+" text,"+
                    user.password+" text,"+
                    user.email+" text,"+
                    user.phone+" text"+");";
    private static final String createAdminTbl =
            "CREATE TABLE  admin ("+ parkTbl.admin_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    parkTbl.adminName+" text,"+
                    parkTbl.adminPassword+" text,"+
                    parkTbl.adminPhone+" text,"+
                    parkTbl.adminEmail+" text"+");";
    private static final String createLocationTbl = "CREATE TABLE location (" +
            "locationid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "locaname TEXT," +
            "perdaycost REAL," +
            " hourlycost REAL," +
            " noofslots INTEGER," +
            "freeslots INTEGER" +
            ");";

    private static final String createSlotRecordTbl = "CREATE TABLE slotrecord (" +
            "slotid INTEGER PRIMARY KEY ," +
            "dayfill INTEGER," +
            "hourfill INTEGER," +
            "location INTEGER," +
            "bdate TEXT," +
            "FOREIGN KEY(slotid) REFERENCES slot(slotid),"+
            "FOREIGN KEY(location) REFERENCES location(locationid));";

    private static final String createSlotTbl =" CREATE TABLE slot (" +
            "slotid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "fill INTEGER," +
            "location INTEGER," +
            "FOREIGN KEY(location) REFERENCES location (locationid)"+
            ");";

    private static final String createDayslotTbl= "CREATE TABLE dayslot (" +
            "slot INTEGER," +
            "location INTEGER," +
            "date TEXT," +
            "PRIMARY KEY(slot)," +
            "FOREIGN KEY(slot) REFERENCES slot(slotid)," +
            "FOREIGN KEY(location) REFERENCES location(locationid));";
    private static final String createHourslotTbl = "CREATE TABLE hourslot (" +
            "slot INTEGER," +
            "location INTEGER," +
            "startdate TEXT," +
            "enddate TEXT," +
            "PRIMARY KEY(slot)," +
            "FOREIGN KEY(slot) REFERENCES slot(slotid)," +
            "FOREIGN KEY(location) REFERENCES location(locationid));";
    private static final String bookingRecordTbl = "CREATE TABLE bookingrecord (" +
            "bookid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "slotid INTEGER," +
            "locationid INTEGER," +
            "userid INTEGER," +
            "date TEXT," +
            "type	INTEGER,"+
            "pay	INTEGER,"+
            "amount	REAL,"+
            "FOREIGN KEY(slotid) REFERENCES slotRecord(slotid)," +
            "FOREIGN KEY(locationid) REFERENCES location(locationid)," +
            "FOREIGN KEY(userid) REFERENCES user(user_id));";

    private static final String delete_user_entries =
            "DROP TABLE IF EXISTS user" ;
    private static final String delete_admin_entries =
            "DROP TABLE IF EXISTS admin" ;
    private static final String delete_locationTbl =
            "DROP TABLE IF EXISTS location ";
    private static final String delete_slotTbl =
            "DROP TABLE IF EXISTS slot " ;
    private static final String delete_dayslot =
            "DROP TABLE IF EXISTS dayslot " ;
    private static final String delete_hourslot =
            "DROP TABLE IF EXISTS hourslot " ;
    private static final String delete_booking_record =
            "DROP TABLE IF EXISTS bookingrecord " ;
    private static final String delete_slot_record =
            "DROP TABLE IF EXISTS slotrecord " ;




    public ParkDb(Context context) {
        super(context, DATABASE_NAME,null, 8);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createUserTbl);
        db.execSQL(createAdminTbl);
        db.execSQL(createLocationTbl);
        db.execSQL(createSlotTbl);
        db.execSQL(createDayslotTbl);
        db.execSQL(createHourslotTbl);
        db.execSQL(createSlotRecordTbl);
        db.execSQL(bookingRecordTbl);


        Log.d("MyDB","created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(delete_user_entries);
        db.execSQL(delete_admin_entries);
        db.execSQL(delete_locationTbl);
        db.execSQL(delete_slotTbl);
        db.execSQL(delete_dayslot);
        db.execSQL(delete_hourslot);
        db.execSQL(delete_booking_record);
        db.execSQL(delete_slot_record);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onUpgrade(db, oldVersion, newVersion);
    }
}
