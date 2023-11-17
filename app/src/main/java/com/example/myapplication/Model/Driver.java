package com.example.myapplication.Model;

public class Driver {
    private String id,name,email,password,location;
    private int rides, rating;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocationNumber() {
        return location;
    }

    public void setLocationNumber(String location) {
        this.location = location;
    }

    public int getRides() {
        return rides;
    }

    public void setRides(int rides) {
        this.rides = rides;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
