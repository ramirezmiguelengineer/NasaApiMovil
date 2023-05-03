package com.ramirezmiguel.myasteroids.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ramirezmiguel.myasteroids.db.SqLiteConnect;
import com.ramirezmiguel.myasteroids.model.interfaces.IUserModel;

public class UserModel implements IUserModel {

    public static final String TABLE_NAME = "users";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+
                                                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                "email TEXT NOT NULL, "+
                                                "encrypted_password TEXT NOT NULL, "+
                                                "first_name TEXT NOT NULL, "+
                                                "last_name TEXT NOT NULL, "+
                                                "identification TEXT NOT NULL, "+
                                                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, "+
                                                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL );";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+" ;";
    private SqLiteConnect database;
    public UserModel(Context context) {
        database = new SqLiteConnect(context);
    }

    @Override
    public User getUser(int id) {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = null;
        User user = new User();
        try {
            cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE id ="+id,null);
            if (cursor.moveToNext()){
                user.setId(cursor.getInt(0));
                user.setEmail(cursor.getString(1));
                user.setEncrypted_password(cursor.getString(2));
                user.setFirst_name(cursor.getString(3));
                user.setLast_name(cursor.getString(4));
                user.setIdentification(cursor.getString(5));
            }
        }catch (Exception e){
            Log.d("Error","Error al getUser email: "+e);
            System.out.println("Error: "+"Error al obtener usuario "+e);
        }finally {
            if (cursor != null){
                cursor.close();
            }
            db.close();
        }
        return user;
    }

    public boolean checkUser(String email){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email from "+TABLE_NAME+" where email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public int getId(String email){
        SQLiteDatabase db = database.getReadableDatabase();
        String[] projection = {"id"};
        String selection = "email=?";
        String[] selectionArgs = {email};
        String sortOrder = "id DESC";
        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        int tableId = -1;
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow("id");
            tableId = cursor.getInt(idIndex);
        }
        cursor.close();
        db.close();
        return tableId;

    }

    public boolean checkPassword(String email, String password){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email, encrypted_password FROM "+TABLE_NAME+" where email = ? AND encrypted_password = ?", new String[]{email,password});
        return cursor.getCount() > 0;
    }

    @Override
    public boolean createUser(User user) {
        try {
            SQLiteDatabase db = database.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("email", user.getEmail());
            values.put("encrypted_password", user.getEncrypted_password());
            values.put("first_name", user.getFirst_name());
            values.put("last_name", user.getLast_name());
            values.put("identification", user.getIdentification());
            db.insert(TABLE_NAME,null,values);
            db.close();
            return true;
        }catch (Exception e){
            Log.d("Error","Error al createUser email: "+e);
            System.out.println("Error: "+"Error al crear usuario "+e);
            return false;
        }
    }

    @Override
    public void updateUser(int id) {

    }

    @Override
    public void removeUser(int id) {

    }
}
