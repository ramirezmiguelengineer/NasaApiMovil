package com.ramirezmiguel.myasteroids.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ramirezmiguel.myasteroids.model.Asteroid;
import com.ramirezmiguel.myasteroids.model.AsteroidModel;
import com.ramirezmiguel.myasteroids.model.interfaces.ApiNeo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AsteroidController {

    public void getAsteroidsApi(Context context){
        //Create model to check if the data was downloaded
        AsteroidModel asteroidModel = new AsteroidModel(context);

        //Consumo Api
        List<Asteroid> asteroids = new ArrayList<Asteroid>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiNeo neoFeedApi = retrofit.create(ApiNeo.class);
        Call<JsonObject> call = neoFeedApi.getNeoFeed("2023-04-27","2023-05-04","Cntegu98Pot0ehMyQodvxhtH4GBlHd6u78pNcbnv");
        if (!asteroidModel.checkData()){
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    System.out.println("Entro al response");
                    JsonObject neoFeed = response.body();
                    JsonObject nearEarthObjects = neoFeed.getAsJsonObject("near_earth_objects");
                    for (Map.Entry<String, JsonElement> entry : nearEarthObjects.entrySet()) {
                        String date = entry.getKey();
                        JsonArray nearEarthObjectsArray = entry.getValue().getAsJsonArray();

                        //Consumir toda la y guardar la informacion que decidi obtener
                        for (JsonElement element : nearEarthObjectsArray) {
                            JsonObject nearEarthObject = element.getAsJsonObject();
                            //Datos a guardar
                            String neo_reference_id, name, close_approach_date_full, orbiting_body;
                            double absolute_magnitude_h;
                            //Obtener
                            neo_reference_id = nearEarthObject.get("neo_reference_id").getAsString();
                            name = nearEarthObject.get("name").getAsString();
                            absolute_magnitude_h = nearEarthObject.get("absolute_magnitude_h").getAsDouble();
                            //System.out.println("Name:"+name+"\n neo:"+neo_reference_id+"\n abso:"+absolute_magnitude_h);

                            close_approach_date_full = nearEarthObject.getAsJsonArray("close_approach_data").get(0).getAsJsonObject().get("close_approach_date_full").getAsString();
                            //System.out.println("Fecha:"+close_approach_date_full);

                            orbiting_body = nearEarthObject.getAsJsonArray("close_approach_data").get(0).getAsJsonObject().get("orbiting_body").getAsString();
                            //System.out.println(orbiting_body);
                            Asteroid asteroid = new Asteroid(neo_reference_id,name,close_approach_date_full);
                            //System.out.println(asteroid.getName());
                            asteroids.add(asteroid);
                        }
                    }
                    SharedPreferences preferences = SharedPreferencesController.getSharedPreferences(context);
                    String email = preferences.getString("email","-1");

                    System.out.println("Asteroids: "+asteroids.size());
                    AsteroidModel a = new AsteroidModel(context);
                    a.createAsteroid(asteroids,email,context);


                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }else {
            System.out.println("La informacion ya esta cargada");
        }
    }
}
