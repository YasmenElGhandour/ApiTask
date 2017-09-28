package com.example.dell.appssquare.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DELL on 26/09/2017.
 */

public class ApiClient {

    public static final String Base_Url="https://api.github.com/";

        public static Retrofit retrofit=null;
        public static Retrofit getApiClient() {
            if(retrofit == null){
                               retrofit= new Retrofit.Builder().baseUrl(Base_Url)
                        .addConverterFactory(GsonConverterFactory.create()).build();


            }
            return retrofit;
        }


}
