package com.example.user.autorate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 13.06.2016.
 */
public class MyDataBase {

    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase database;

    private static final String DB_NAME = "AutoRate";
    private static final String TABLE_NAME = "AUTOCOLOR";

    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String PRICE = "PRICE";


    private static final int DB_VERSION = 1;

    public MyDataBase(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }
    public String getDataBaseName(){
        return TABLE_NAME;
    }

    public Cursor getAllItems() {
        database = dbOpenHelper.getReadableDatabase();
        return database.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void close() {
        if (dbOpenHelper != null) dbOpenHelper.close();
        if (database != null) database.close();
    }
    public class DBOpenHelper extends SQLiteOpenHelper {



        public DBOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT, "
                    + DESCRIPTION + " TEXT, "
                    + PRICE + " INTEGER) ;");
            insertService(db, "AUTOCOLOR", "Шиномонтаж", "Замена четырех колес", 4000);
            insertService(db, "AUTOCOLOR", "Кузовные работы", "Замена чего то там", 10000);
            insertService(db, "AUTOCOLOR", "Диагностика", "Продиагностировать чего-нибудь", 3000);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        private void insertService(SQLiteDatabase db, String currentDataBaseName, String name, String description, int price) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, name);
            contentValues.put(DESCRIPTION, description);
            contentValues.put(PRICE , price);
            db.insert(currentDataBaseName, null, contentValues);
        }
    }
}
