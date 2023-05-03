package com.ramirezmiguel.myasteroids.model;

import java.util.Arrays;

public class Asteroid {
    //Create Asteroid to keep the data on the Database, when the user press download button we create Asteroid and can save by the model
    //only this data will saved on the database

    private String neo_reference_id, name, close_approach_date_full, orbiting_body;
    private double absolute_magnitude_h;
    //This data is filled by the api
    private double[][]  diameter; //inside it'll have km(min, max), mts(min, max)...
    private boolean potentially_hazardous, is_sentry_object;
    private double[] relative_velocity, miss_distance;

    public Asteroid() {
        // Can create a empty asteroid and after that we can setter all the data
    }

    public Asteroid(String neo_reference_id, String name, String close_approach_date_full) {
        //Constructor used to save Asteroid on the database
        this.neo_reference_id = neo_reference_id;
        this.name = name;
        this.close_approach_date_full = close_approach_date_full;
    }

    public Asteroid(String neo_reference_id, String name, String close_approach_date_full, String orbiting_body, double absolute_magnitude_h, double[][] diameter, boolean potentially_hazardous, boolean is_sentry_object, double[] relative_velocity, double[] miss_distance) {
        this.neo_reference_id = neo_reference_id;
        this.name = name;
        this.close_approach_date_full = close_approach_date_full;
        this.orbiting_body = orbiting_body;
        this.absolute_magnitude_h = absolute_magnitude_h;
        this.diameter = diameter;
        this.potentially_hazardous = potentially_hazardous;
        this.is_sentry_object = is_sentry_object;
        this.relative_velocity = relative_velocity;
        this.miss_distance = miss_distance;
    }

    public String getNeo_reference_id() {
        return neo_reference_id;
    }

    public void setNeo_reference_id(String neo_reference_id) {
        this.neo_reference_id = neo_reference_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClose_approach_date_full() {
        return close_approach_date_full;
    }

    public void setClose_approach_date_full(String close_approach_date_full) {
        this.close_approach_date_full = close_approach_date_full;
    }

    public String getOrbiting_body() {
        return orbiting_body;
    }

    public void setOrbiting_body(String orbiting_body) {
        this.orbiting_body = orbiting_body;
    }

    public double getAbsolute_magnitude_h() {
        return absolute_magnitude_h;
    }

    public void setAbsolute_magnitude_h(double absolute_magnitude_h) {
        this.absolute_magnitude_h = absolute_magnitude_h;
    }

    public double[][] getDiameter() {
        return diameter;
    }

    public void setDiameter(double[][] diameter) {
        this.diameter = diameter;
    }

    public boolean isPotentially_hazardous() {
        return potentially_hazardous;
    }

    public void setPotentially_hazardous(boolean potentially_hazardous) {
        this.potentially_hazardous = potentially_hazardous;
    }

    public boolean isIs_sentry_object() {
        return is_sentry_object;
    }

    public void setIs_sentry_object(boolean is_sentry_object) {
        this.is_sentry_object = is_sentry_object;
    }

    public double[] getRelative_velocity() {
        return relative_velocity;
    }

    public void setRelative_velocity(double[] relative_velocity) {
        this.relative_velocity = relative_velocity;
    }

    public double[] getMiss_distance() {
        return miss_distance;
    }

    public void setMiss_distance(double[] miss_distance) {
        this.miss_distance = miss_distance;
    }

    @Override
    public String toString() {
        return "Asteroid{" +
                "neo_reference_id='" + neo_reference_id + '\'' +
                ", name='" + name + '\'' +
                ", close_approach_date_full='" + close_approach_date_full + '\'' +
                ", orbiting_body='" + orbiting_body + '\'' +
                ", absolute_magnitude_h=" + absolute_magnitude_h +
                ", diameter=" + Arrays.toString(diameter) +
                ", potentially_hazardous=" + potentially_hazardous +
                ", is_sentry_object=" + is_sentry_object +
                ", relative_velocity=" + Arrays.toString(relative_velocity) +
                ", miss_distance=" + Arrays.toString(miss_distance) +
                '}';
    }
}
