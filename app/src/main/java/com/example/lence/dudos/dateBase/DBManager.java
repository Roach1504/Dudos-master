package com.example.lence.dudos.dateBase;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lence.dudos.model.UserModel;
import com.example.lence.dudos.view.MVPDB;
import com.example.lence.dudos.view.MVPUpDate;
import com.example.lence.dudos.view.Main2Activity;

import java.util.ArrayList;
import java.util.List;

public class DBManager implements MVPDB{
    DB db;
    SQLiteDatabase database;
    MVPUpDate mvpUpDate;
    public DBManager(Main2Activity mvpUpDate){
        db = new DB(mvpUpDate);
        this.mvpUpDate =mvpUpDate;
    }

    @Override
    public void insert(UserModel insertUser) {
        database = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB.KEY_NAME, insertUser.getName());
        contentValues.put(DB.KEY_AGE, insertUser.getAge());
        contentValues.put(DB.KEY_GENDER, insertUser.getGender());
        contentValues.put(DB.KEY_MARRIED, insertUser.getMarried());
        database.insert(DB.TABLE_USER, null, contentValues);
        mvpUpDate.showNewUser();
    }

    @Override
    public List<UserModel> getUsers() {
        database = db.getReadableDatabase();
        List<UserModel> rez = new ArrayList<>();
        Cursor cursor = database.query(DB.TABLE_USER, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            int key_id = cursor.getColumnIndex(DB.KEY_USER_ID);
            int key_name = cursor.getColumnIndex(DB.KEY_NAME);
            int key_age = cursor.getColumnIndex(DB.KEY_AGE);
            int key_gender = cursor.getColumnIndex(DB.KEY_GENDER);
            int key_married = cursor.getColumnIndex(DB.KEY_MARRIED);
            do{
                UserModel item = new UserModel();
                item.setId(cursor.getString(key_id));
                item.setName(cursor.getString(key_name));
                item.setAge(cursor.getString(key_age));
                item.setGender(cursor.getString(key_gender));
                item.setMarried(cursor.getString(key_married));
                rez.add(item);
            }
            while (cursor.moveToNext());
        }
        else{
            return rez;
        }
        return rez;
    }

    @Override
    public void delete(String id) {
        database = db.getReadableDatabase();
        database.delete(DB.TABLE_USER, DB.KEY_USER_ID+" =?", new String[] {id});
        mvpUpDate.showNewUser();
    }

    @Override
    public void upDate(String id, UserModel upUser) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB.KEY_NAME, upUser.getName());
        contentValues.put(DB.KEY_AGE, upUser.getAge());
        contentValues.put(DB.KEY_GENDER, upUser.getGender());
        contentValues.put(DB.KEY_MARRIED, upUser.getMarried());
        contentValues.put(DB.KEY_USER_ID, upUser.getId());
        database.update(DB.TABLE_USER, contentValues, DB.KEY_USER_ID + " = "+id, null);
        mvpUpDate.showNewUser();
    }
}
