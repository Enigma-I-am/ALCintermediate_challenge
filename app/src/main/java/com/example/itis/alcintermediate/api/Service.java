package com.example.itis.alcintermediate.api;

import com.example.itis.alcintermediate.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by enigma on 8/21/17.
 */

public interface Service {

    @GET("/search/users?q=language:java+location:lagos")
    Call<ItemResponse> getItems();

}
