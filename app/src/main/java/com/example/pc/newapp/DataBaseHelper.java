package com.example.pc.newapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pc on 06/02/2017.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TAG = DataBaseHelper.class.getSimpleName();
    public  static  final  String DB_NAME = "myapp.db";
    public  static  final  int DB_VERSION =1 ;
    public static final String USER_TABLE ="users";
    public static final String COLUMN_ID ="id";
    public static final String COLUMN_NAME ="name";
    public static final String COLUMN_PASS ="password";
    public static final String COLUMN_SPORTS="sports";
    public static final String COLUMN_ARTS="arts";
    public static final String COLUMN_Music="music";
    public static final String COLUMN_Read="read";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"+ COLUMN_PASS + " TEXT,"+ COLUMN_SPORTS + " TEXT,"+ COLUMN_ARTS
            + " TEXT,"+ COLUMN_Music + " TEXT,"
            + COLUMN_Read + " TEXT);";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ USER_TABLE);
        onCreate(db);
    }
    // store data
    public void addUser(String name ,String  password ,String sports ,String arts,String music ,String read){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASS,password );
        values.put(COLUMN_SPORTS, sports);
        values.put(COLUMN_ARTS, arts);
        values.put(COLUMN_Music,music);
        values.put(COLUMN_Read, read);

        long id = db.insert(USER_TABLE,null,values);
        db.close();
        Log.d(TAG, "user inserted" + id);
    }
    public boolean getUser(String name ,String  password){
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_NAME + " = " + "'"+name+"'" + " and " + COLUMN_PASS + " = " + "'"+password+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public boolean checkUser_Name(String name){
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_NAME + " = " + "'"+name+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

   public String[] getUser_Name(String name){
      String selectQuery = "select * from  " + USER_TABLE + " where " +
               COLUMN_NAME + " = " + "'"+name+"'";
        SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);
       String []options =new String[4] ;
       if (cursor.moveToFirst()) {
           cursor.moveToFirst();
           options[0] =cursor.getString(cursor.getColumnIndex(COLUMN_SPORTS));
           options[1]=cursor.getString(cursor.getColumnIndex(COLUMN_ARTS));
           options[2]=cursor.getString(cursor.getColumnIndex(COLUMN_Music));
           options[3]=cursor.getString(cursor.getColumnIndex(COLUMN_Read));
           cursor.close();
           }else{
           options = null;
       }
       db.close();
       return options;
    }
}
