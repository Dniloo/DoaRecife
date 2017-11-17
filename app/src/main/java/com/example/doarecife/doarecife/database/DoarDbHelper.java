package com.example.doarecife.doarecife.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DoarDbHelper extends SQLiteOpenHelper{

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "doar_db";

    public DoarDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
     public void onCreate(SQLiteDatabase db){
      db.execSQL("CREATE TABLE " + DoarContract.TABLE_NAME +" (" +
              DoarContract._ID      +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
              DoarContract.LOCAL   +" TEXT NOT NULL, " +
              DoarContract.TIPO    +" TEXT NOT NULL, " +
              DoarContract.QUANTIDADE   +" INTEGER  NOT NULL, " +
              DoarContract.FOTO     +" TEXT NOT NULL)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

