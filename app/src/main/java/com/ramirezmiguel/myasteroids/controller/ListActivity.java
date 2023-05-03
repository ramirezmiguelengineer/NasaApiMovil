package com.ramirezmiguel.myasteroids.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ramirezmiguel.myasteroids.MainActivity;
import com.ramirezmiguel.myasteroids.R;
import com.ramirezmiguel.myasteroids.model.AsteroidModel;
import com.ramirezmiguel.myasteroids.model.UserModel;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    ExtendedFloatingActionButton close;
    FloatingActionButton  download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        context = getApplicationContext();
        app();
    }
    protected void app(){
        close = findViewById(R.id.closeS);
        download = findViewById(R.id.downloadB);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.closeS){
            closeSession();


        }
        if (v.getId() == R.id.downloadB){
            AsteroidController asteroidController = new AsteroidController();
            asteroidController.getAsteroidsApi(context);
        }

    }

    private void closeSession(){
        SharedPreferences preferences = SharedPreferencesController.getSharedPreferences(context);
        boolean isLoggedIn  = preferences.getBoolean("isLoggedIn",false);
        if (isLoggedIn) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}