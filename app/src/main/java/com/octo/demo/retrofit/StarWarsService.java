package com.octo.demo.retrofit;

import com.octo.demo.model.Character;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StarWarsService {

    @GET("people/{id}/")
    Call<Character> getCharacterById(@Path("id") int id);
}
