package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.Model.Driver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    private Button Btn_back, Btn_register;

    private RadioButton radioEmployee, radioDriver;

    private EditText ID,Name,Email,Password,LocationNumber;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Btn_back = (Button) findViewById(R.id.btn_back);
        Btn_register = (Button) findViewById(R.id.Confirm_register);
        radioEmployee = (RadioButton) findViewById(R.id.employee_role);
        radioDriver = (RadioButton) findViewById(R.id.driver_role);
        ID = (EditText) findViewById(R.id.id_entry);
        Name = (EditText) findViewById(R.id.name_entry);
        Password = (EditText) findViewById(R.id.password_entry);
        LocationNumber = (EditText) findViewById(R.id.location_entry);
        Email = (EditText) findViewById(R.id.email_entry);
        Btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Henao", "Se presiono el boton");

                if (radioDriver.isChecked()) {
                    Driver driver = new Driver();
                    driver.setID(ID.getText().toString());
                    driver.setName(Name.getText().toString());
                    driver.setEmail(Email.getText().toString());
                    driver.setPassword(Password.getText().toString());
                    driver.setLocationNumber(LocationNumber.getText().toString());
                    driver.setRides(0);
                    driver.setRating(0);

                    String iddriver = driver.getID();
                    String loca = driver.getLocationNumber();

                    Log.e("Henao", "ID:"+ iddriver);
                    Log.e("Henao", "Location"+ loca);

                    RegisterDriver(driver);
                }
                return;
            }
        });
    }

    public void RegisterDriver(Driver driver){

        Log.e("Henao", "Se mando a registro");

        String ipAddress = LocalIP.localip;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + ipAddress + ":8080/server/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);


        Call<Void>call = apiService.registerDriver(driver);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.e("Henao", "Registro exitoso");
                    Toast.makeText(Register.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                } else {
                    if (response.code() == 400) {
                        Toast.makeText(Register.this, "Ya existe un usuario con ese Email o ID", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "Registro Fallado", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Register.this, "Error de Conexión:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                String error = t.getMessage();
                Log.e("Error", error);
                System.out.println(error);
            }
        });


    }

}