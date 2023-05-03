package com.ramirezmiguel.myasteroids.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ramirezmiguel.myasteroids.model.AsteroidModel;
import com.ramirezmiguel.myasteroids.model.User;
import com.ramirezmiguel.myasteroids.model.UserModel;

public class SqLiteConnect extends SQLiteOpenHelper {
    private static final String DB_NAME = "nasa_asteroids";
    private static final int DB_VERSION = 2;

    public SqLiteConnect(Context context) {
        super(context, DB_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserModel.CREATE_TABLE);
        db.execSQL(AsteroidModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("OnUpgrade");
        db.execSQL(UserModel.DROP_TABLE);
        db.execSQL(AsteroidModel.DROP_TABLE);
        onCreate(db);
    }
}
