package com.epul.androidcinema.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.epul.androidcinema.MainActivity;
import com.epul.androidcinema.R;
import com.epul.androidcinema.domain.LoginParam;
import com.epul.androidcinema.service.Ambiances;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ActivityMonitor extends AppCompatActivity implements View.OnClickListener {

    private Button btnGetData;
    private ArrayAdapter arrayAdapter;
    private ListView listView;
    private String[] records = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On se relie au design
        setContentView(R.layout.activity_monitor);

        btnGetData = (Button) findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);;
    }

    @Override
    public void onClick(View v) {

        //Intent intent = new Intent(ActivityMonitor.this, MainActivity.class);

        if(v == btnGetData) {
            try {
                Callback callback = new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response){
                        victoire(response);
                    }
                };

                MainActivity.callApi(Ambiances.getData, callback);

                listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, records));
                listView.invalidate();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void victoire(Response response){
        try {
            JSONObject obj = new JSONObject(response.body().string());

            JSONArray luminosity = obj.getJSONArray("luminosity");
            JSONArray sound = obj.getJSONArray("sound");
            JSONArray presence = obj.getJSONArray("presence");

            if(luminosity == null){
                records = new String[0];
                return;
            }

            records = new String[1];
            //records = new String[luminosity.length()];

            for (int i = 0; i < luminosity.length() && i < 1; i++)
            {
                records[i] = "Luminosité : " + luminosity.getInt(i)
                        + " - " + ((sound.getInt(i) == 1)?"Présence detectée":"Pas de présence")
                        + " - " + ((presence.getInt(i) == 1)?"Son détecté":"Pas de son");
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}