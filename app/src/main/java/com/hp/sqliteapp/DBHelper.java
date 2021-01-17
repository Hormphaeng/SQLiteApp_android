package com.hp.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Employee.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Employees(id TEXT primary key, name TEXT, surname TEXT, tel TEXT, email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop table if exists Employees");

    }

    public Boolean insertemployeedata(String id, String name, String surname, String tel, String email ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("surname", surname);
        contentValues.put("tel", tel);
        contentValues.put("email", email);
        long result = DB.insert("Employees", null, contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean updateemployeedata(String id, String name, String surname, String tel, String email ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("surname", surname);
        contentValues.put("tel", tel);
        contentValues.put("email", email);
        Cursor cursor = DB.rawQuery("Select * from Employees where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("Employees", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String id ) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Employees where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Employees", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Employees ", null);
        return cursor;
    }

}
