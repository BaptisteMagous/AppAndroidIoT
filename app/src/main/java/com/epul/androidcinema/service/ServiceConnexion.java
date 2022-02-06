package com.epul.androidcinema.service;

import com.epul.androidcinema.domain.LoginParam;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceConnexion {

    // requête de contrôle
    @POST("auth/signin")
    Call<Object> getConnexion(@Body LoginParam unL);

}
