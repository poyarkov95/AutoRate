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
    private static final String SECOND_TABLE_NAME = "AUTORATE";
    private static final String THIRD_TABLE_NAME = "DILSAUTO";

    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String PRICE = "PRICE";


    private static final int DB_VERSION = 1;
    //database fields
    String[] columns;

    public MyDataBase(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }


    public Cursor getAllItems(String tableName) {
        database = dbOpenHelper.getReadableDatabase();
        return database.query(tableName, null, null, null, null, null, null);
    }
    public Cursor getTireItems(String tableName) {
        columns = new String[]{"_id","NAME","DESCRIPTION","PRICE"};
        database = dbOpenHelper.getReadableDatabase();
        return database.query(tableName, columns, "NAME = ?", new String[]{"Шиномонтаж"}, null, null, null);
    }

    public Cursor getBodyItems(String tableName){
        columns = new String[]{"_id","NAME","DESCRIPTION","PRICE"};
        database = dbOpenHelper.getReadableDatabase();
        return database.query(tableName, columns, "NAME = ?", new String[]{"Кузовные работы"}, null, null, null);
    }

    public Cursor getDiagnostic(String tableName){
        columns = new String[]{"_id","NAME","DESCRIPTION","PRICE"};
        database = dbOpenHelper.getReadableDatabase();
        return database.query(tableName, columns, "NAME = ?", new String[]{"Диагностика"}, null, null, null);
    }
    public void updateDB(String s){
        String name = "";
        String service = "";
        String discount = "";
        String[]line = s.split(" ");
        for(int i = 0; i < line.length; i++){
            name = line[0];
            service = line[1];
            discount = line[2];
        }
        database = dbOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PRICE",String.valueOf(discount));
        database.update(name,contentValues,"NAME = ?",new String[]{service});
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
            db.execSQL("CREATE TABLE " + SECOND_TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT, "
                    + DESCRIPTION + " TEXT, "
                    + PRICE + " INTEGER) ;");
            insertService(db, "AUTORATE", "Шиномонтаж", "Замена четырех колес", 5000);
            insertService(db, "AUTORATE", "Кузовные работы", "Замена чего то там", 12000);
            insertService(db, "AUTORATE", "Диагностика", "Продиагностировать чего-нибудь", 4000);


            db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT, "
                    + DESCRIPTION + " TEXT, "
                    + PRICE + " INTEGER) ;");
            insertService(db, "AUTOCOLOR", "Шиномонтаж", "Замена четырех колес", 4000);
            insertService(db, "AUTOCOLOR", "Кузовные работы", "Замена чего то там", 10000);
            insertService(db, "AUTOCOLOR", "Диагностика", "Продиагностировать чего-нибудь", 3000);

            db.execSQL("CREATE TABLE " + THIRD_TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT, "
                    + DESCRIPTION + " TEXT, "
                    + PRICE + " INTEGER) ;");
            insertService(db, "DILSAUTO", "Шиномонтаж", "Замена четырех колес", 18000);
            insertService(db, "DILSAUTO", "Кузовные работы", "Замена чего то там", 1337228);
            insertService(db, "DILSAUTO", "Диагностика", "Продиагностировать чего-нибудь", 3000);
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
