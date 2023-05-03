package com.ramirezmiguel.myasteroids.model;

public class User {
    //Create a User and this user can be used to saved on the database SQLite
    private int id;
    private String email, encrypted_password, first_name, last_name, identification;

    public User() {
        // Can create a empty user and after that we can setter all the data
    }

    public User(int id, String email, String encrypted_password, String first_name, String last_name, String identification) {
        this.id = id;
        this.email = email;
        this.encrypted_password = encrypted_password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.identification = identification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", encrypted_password='" + encrypted_password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", identification='" + identification + '\'' +
                '}';
    }
}
