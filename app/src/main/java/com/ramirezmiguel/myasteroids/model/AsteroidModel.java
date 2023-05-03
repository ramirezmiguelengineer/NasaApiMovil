package com.ramirezmiguel.myasteroids.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.ramirezmiguel.myasteroids.db.SqLiteConnect;
import com.ramirezmiguel.myasteroids.model.interfaces.IAsteroidModel;

import java.util.ArrayList;
import java.util.List;

public class AsteroidModel implements IAsteroidModel {

    public static final String TABLE_NAME = "asteroids";
    public static final String CREATE_TABLE ="CREATE TABLE asteroids ( "+
                                                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                "neo_reference_id TEXT, "+
                                                "name TEXT, "+
                                                "close_approach_date_full TEXT, "+
                                                "id_user_fk INTEGER, "+
                                                "FOREIGN KEY(id_user_fK) REFERENCES users(id));";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS asteroids;";

    private SqLiteConnect database;

    public AsteroidModel(Context context) {
        database = new SqLiteConnect(context);
    }

    public List<Asteroid> getAsteroids(){
        try {
            SQLiteDatabase db = database.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT neo_reference_id, name, close_approach_date_full FROM "+
                                        TABLE_NAME+";",null);
            List<Asteroid> asteroids = new ArrayList<>();
            if (cursor.moveToFirst()){
                System.out.println("If");
                do{
                    System.out.println("do while");
                    String neo = cursor.getString(cursor.getColumnIndexOrThrow("neo_reference_id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String close = cursor.getString(cursor.getColumnIndexOrThrow("close_approach_date_full"));
                    Asteroid asteroid = new Asteroid(neo, name, close);
                    asteroids.add(asteroid);
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            System.out.println("Lo envio");
            return asteroids;
        }catch (Exception e){
            Log.d("Error","Error al getasteroids: "+e);
            System.out.println("Error: "+"Error al getasteroids "+e);
        }
        return null;
    }
    @Override
    public Asteroid getAsteroid(int id) {
        return null;
    }

    @Override
    public Asteroid getAsteroid(int id_api, boolean api) {
        return null;
    }

    @Override
    public List<Asteroid> list() {
        return null;
    }

    @Override
    public void createAsteroid(List<Asteroid> asteroids, String user, Context context) {
        if (!checkData()) {
            UserModel userModel = new UserModel(context);
            int id_user = userModel.getId(user);
            try {
                for (Asteroid asteroid : asteroids) {
                    SQLiteDatabase db = database.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("neo_reference_id", asteroid.getNeo_reference_id());
                    values.put("name", asteroid.getName());
                    values.put("close_approach_date_full", asteroid.getClose_approach_date_full());
                    values.put("id_user_fk", id_user);
                    db.insert(TABLE_NAME, null, values);
                    db.close();
                }
            } catch (Exception e) {
                Log.d("Error", "Error al createAsteroid: " + e);
                System.out.println("Error: " + "Error al createAsteroid " + e);
            }
        }else {
            System.out.println("Ya esta cargado");
        }

    }

    public boolean checkData(){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name from "+TABLE_NAME+" ;",null);
        return cursor.getCount() > 0;
    }

    @Override
    public void updateAsteroid(int id, Asteroid asteroid) {

    }

    @Override
    public void removeAsteroid(int id) {

    }
}
