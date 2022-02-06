package com.epul.androidcinema.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.epul.androidcinema.MainActivity;
import com.epul.androidcinema.R;
import com.epul.androidcinema.service.Ambiances;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ActivityAmbiances extends AppCompatActivity implements View.OnClickListener {

    private Button btnAmbianceRed;
    private Button btnAmbianceBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On se relie au design
        setContentView(R.layout.activity_ambiances);

        btnAmbianceRed = (Button) findViewById(R.id.btnAmbianceRed);
        btnAmbianceBlue = (Button) findViewById(R.id.btnAmbianceBlue);

        btnAmbianceRed.setOnClickListener(this);
        btnAmbianceBlue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // Intent intent = new Intent(ActivityAmbiances.this, MainActivity.class);

        if(v == btnAmbianceRed){
            try {

                Callback callback = new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response){
                    }
                };
                MainActivity.callApi(Ambiances.rouge, callback);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Ambiance changée", Toast.LENGTH_SHORT).show();
        }

        else if(v == btnAmbianceBlue){
            try {

                Callback callback = new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response){
                    }
                };

                MainActivity.callApi(Ambiances.bleue, callback);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Ambiance changée", Toast.LENGTH_SHORT).show();
        }

    }
}