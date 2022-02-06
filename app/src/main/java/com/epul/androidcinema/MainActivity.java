package com.epul.androidcinema;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epul.androidcinema.presentation.ActivityAmbiances;
import com.epul.androidcinema.presentation.ActivityConnexion;
import com.epul.androidcinema.presentation.ActivityMonitor;
import com.epul.androidcinema.service.SessionManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConnexion;
    private Button btnMonitor;
    private Button btnGoToAmbiances;

    private TextView txtInfo;
    private String token;

    private SessionManager session;

    private static OkHttpClient okHttpClient = new OkHttpClient();

    private static MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnexion = findViewById(R.id.btnConnexion);
        btnMonitor = findViewById(R.id.btnMonitor);
        btnGoToAmbiances = findViewById(R.id.btnGoToAmbiances);

        btnConnexion.setOnClickListener(this);
        btnMonitor.setOnClickListener(this);
        btnGoToAmbiances.setOnClickListener(this);

        btnConnexion.setEnabled(true);
        btnMonitor.setEnabled(true);
        btnGoToAmbiances.setEnabled(true);

       /* this.txtInfo = findViewById(R.id.txtInfo);
        this.token = null;
        this.btnConnexion.setText("Connexion");
        this.txtInfo.setText("");*/

        session = new SessionManager(this);
    }

    public void onClick(View v) {

        if (v == btnConnexion) {
            Intent intent = new Intent(MainActivity.this, ActivityConnexion.class);
            startActivityForResult(intent, 1);
        }
        else if (v == btnMonitor) {
            Intent intent = new Intent(MainActivity.this, ActivityMonitor.class);
            startActivityForResult(intent, 0);
        }
        else if (v == btnGoToAmbiances) {
            Intent intent = new Intent(MainActivity.this, ActivityAmbiances.class);
            startActivityForResult(intent, 0);
        }
/*
        else if (v == ){
            callApi(Ambiances.rouge);
            ((TextView) findViewById(R.id.tvTitle)).setText(R.string.mode1);
        }
        else if (v == ){
            callApi(Ambiances.bleue);
            ((TextView) findViewById(R.id.tvTitle)).setText(R.string.mode2);
        }*/

        else {
            // display error
            String erreur = "erreur!";
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            switch (resultCode) {
                case MainActivity.RESULT_OK: {
                    if (data.hasExtra("unToken")) {
                        String  unToken = (String) data.getSerializableExtra("unToken");
                        this.txtInfo.setText(unToken);
                        // on sauve le token en session
                        session.saveAuthToken(unToken);
                        // on met à jour les boutons
                        this.btnConnexion.setEnabled(false);
                        break;
                    } else
                        super.finish();
                }

                case 2   :  {
                    // information = (String) data.getExtras().get("information");
                    // this.txtInfo.setText(information);
                    this.btnConnexion.setEnabled(false);
                    break;

                }
            }
        }
    }



    public static void callApi(String body, Callback callback) throws IOException {
        MainActivity.callApi(body, callback, "http://192.168.43.129:1023");
    }

    public static void callApi(String body, Callback callback, String url) throws IOException {
        RequestBody requestBody = RequestBody.create(body, JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(callback);
    }
}