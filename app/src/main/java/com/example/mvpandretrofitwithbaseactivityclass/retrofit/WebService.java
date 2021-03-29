package com.example.mvpandretrofitwithbaseactivityclass.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private static final String BASE_URL="enter your Base Url";
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build());

    private static final Retrofit  retrofit = retrofitBuilder.build();

    public static  <S> S createService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}