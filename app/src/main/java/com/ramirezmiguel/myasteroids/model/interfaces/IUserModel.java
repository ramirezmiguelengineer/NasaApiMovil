package com.ramirezmiguel.myasteroids.model.interfaces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ramirezmiguel.myasteroids.model.UserModel;
import com.ramirezmiguel.myasteroids.model.User;

public interface IUserModel {
    User getUser(int id); //Return the user by the id
    boolean createUser(User user); //Create an user
    void updateUser(int id); //Update the user data by the giving id
    void removeUser(int id); //Remove an user by the giving by
}
