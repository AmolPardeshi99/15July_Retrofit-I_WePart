package com.example.a15july_retrofit_i_wepart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    //keep all three name(userId) same
    @GET("/api/users/{userId}")
    Call<ResponseModel> getUser(@Path("userId") int userId);



}
