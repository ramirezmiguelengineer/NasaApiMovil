package com.ramirezmiguel.myasteroids.model.interfaces;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiNeo {
    @GET("neo/rest/v1/feed")
    Call<JsonObject> getNeoFeed(
            @Query("start_date") String startDate,
            @Query("end_date") String endDate,
            @Query("api_key") String apiKey
    );

}
