package com.route.newsapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohamed Nabil Mohamed on 10/17/2019.
 * m.nabil.fci2015@gmail.com
 */
public class ApiManager {

    private static Retrofit retrofit;

    private static Retrofit getInstance(){
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }
        return retrofit;
    }

    public static WebServices getApis(){
        return getInstance().create(WebServices.class);
    }
}
