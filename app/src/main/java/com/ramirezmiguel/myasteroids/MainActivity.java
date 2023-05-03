package com.ramirezmiguel.myasteroids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ramirezmiguel.myasteroids.controller.ListActivity;
import com.ramirezmiguel.myasteroids.controller.LoginActivity;
import com.ramirezmiguel.myasteroids.controller.RegisterActivity;
import com.ramirezmiguel.myasteroids.controller.SharedPreferencesController;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    Button lButton, sButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Check if user still login
        context = getApplicationContext();
        SharedPreferences preferences = SharedPreferencesController.getSharedPreferences(context);
        boolean isLoggedIn  = preferences.getBoolean("isLoggedIn",false);
        System.out.println("-->"+isLoggedIn);
        if (isLoggedIn){
            System.out.println("Entro a login de login");
            Intent intent = new Intent(context, ListActivity.class);
            startActivity(intent);
            finish();
        }else {
            //Show the Login as a first view
            setContentView(R.layout.activity_main);
            //Load as variable the buttons
            app();
        }
    }

    private void app(){
        context = getApplicationContext();
        //Load the important actions to sen a visual answer
        lButton = (Button) findViewById(R.id.BotonLogin);
        sButton = findViewById(R.id.BotonRegister);
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.BotonLogin){
            Intent iLogin = new Intent(context, LoginActivity.class);
            startActivity(iLogin);
        }
        if (v.getId() == R.id.BotonRegister){
            Intent iRegister = new Intent(context, RegisterActivity.class);
            startActivity(iRegister);
        }
    }
}