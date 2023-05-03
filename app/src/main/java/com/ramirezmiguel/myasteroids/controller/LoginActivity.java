package com.ramirezmiguel.myasteroids.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ramirezmiguel.myasteroids.R;
import com.ramirezmiguel.myasteroids.model.UserModel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;

    EditText email, password;
    Button loginButton;
    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        SharedPreferences preferences = SharedPreferencesController.getSharedPreferences(context);
        boolean isLoggedIn  = preferences.getBoolean("isLoggedIn",false);
        System.out.println("-L->"+isLoggedIn);
        if (isLoggedIn){
            Intent intent = new Intent(context, ListActivity.class);
            startActivity(intent);
            finish();
        }else {
            setContentView(R.layout.activity_login);
            app();
        }
    }

    protected void app(){
        context  = getApplicationContext();
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        loginButton  = findViewById(R.id.loginButton);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton){
            if (!checkTextFields()){
                userModel = new UserModel(context);
                String checkEmail, checkPassword;
                checkEmail = email.getText().toString();
                checkPassword = password.getText().toString();
                checkPassword = toHash(checkPassword);
                if(userModel.checkPassword(checkEmail,checkPassword)){
                    //hacer el inicio de sesion y mandarlo a la pagina principal sin que se me devuelva

                    context = getApplicationContext();
                    SharedPreferences preferences = SharedPreferencesController.getSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("email",checkEmail);
                    editor.apply();

                    Intent intent = new Intent(context, ListActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "Incorrect login information", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private boolean checkTextFields(){
        boolean check = false;
        List<EditText> emptyFields = new ArrayList<>();
        EditText[] editTexts = {email, password};

        for (EditText editText : editTexts) {
            if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                editText.setError("Mandatory");
                emptyFields.add(editText);
                check = true;
            }
        }

        if (check) {
            StringBuilder message = new StringBuilder();
            message.append("Please fill in the following fields:");
            for (EditText editText : emptyFields) {
                message.append("\n- ").append(editText.getHint());
            }
            Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
        }

        return check;
    }

    private String toHash(String input){
        String hashString = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            hashString = String.format("%064x",new BigInteger(1, digest.digest()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return hashString;
    }
}