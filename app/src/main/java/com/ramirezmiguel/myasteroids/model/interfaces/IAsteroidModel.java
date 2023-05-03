package com.ramirezmiguel.myasteroids.model.interfaces;

import android.content.Context;

import com.ramirezmiguel.myasteroids.model.Asteroid;

import java.util.List;

public interface IAsteroidModel {
    Asteroid getAsteroid(int id); //Return the Asteroids by the id
    Asteroid getAsteroid(int id_api, boolean api); //Return the Asteroids by the id api
    List<Asteroid> list(); //Return all the asteroids
    void createAsteroid(List<Asteroid> asteroids, String user, Context context); //Create an Asteroids
    void updateAsteroid(int id, Asteroid asteroid); //Update the Asteroids data by the giving id
    void removeAsteroid(int id); //Remove an Asteroids by the giving by
}
