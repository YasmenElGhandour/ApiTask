package com.example.dell.appssquare.Rest;

import com.example.dell.appssquare.Model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by DELL on 26/09/2017.
 */

public interface ApiInterface {

    @GET("users/square/repos")
    Call<List<Repository>> getRepose();




}
