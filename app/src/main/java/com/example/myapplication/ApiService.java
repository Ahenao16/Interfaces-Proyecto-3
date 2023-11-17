package com.example.myapplication;

import com.example.myapplication.Model.Driver;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("driver/register")
    Call<Void> registerDriver(@Body Driver driver);
}
