package com.ramirezmiguel.myasteroids.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ramirezmiguel.myasteroids.R;
import com.ramirezmiguel.myasteroids.model.User;
import com.ramirezmiguel.myasteroids.model.UserModel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    //Objets in the layout
    EditText f_name, l_name, email, password, identification;
    Button registerButton;
    User user;
    UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        app();
    }

    protected void app(){
        context = getApplicationContext();
        f_name = findViewById(R.id.firstNameText);
        l_name = findViewById(R.id.lastNameText);
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        identification = findViewById(R.id.identificationText);
        registerButton = findViewById(R.id.signinButton);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signinButton){
            boolean check = checkTextFields();
            if(!check){
                //Get data and saved on the DB
                //Crate and User Object
                user = new User();
                user.setFirst_name(f_name.getText().toString());
                user.setLast_name(l_name.getText().toString());
                user.setEmail(email.getText().toString());
                String hashPassword = toHash(password.getText().toString());
                user.setEncrypted_password(hashPassword);
                user.setIdentification(identification.getText().toString());
                //Create row on DB
                userModel = new UserModel(context);
                if (userModel.createUser(user)){
                    //Clean the fields, make login and sen user to main page
                }else {
                    System.out.println("No se Creo Usuario");
                }
            }
        }
    }

    private boolean checkTextFields(){
        boolean check = false;
        List<EditText> emptyFields = new ArrayList<>();
        EditText[] editTexts = {f_name, l_name, email, password, identification};

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