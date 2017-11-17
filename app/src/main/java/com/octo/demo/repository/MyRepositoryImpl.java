package com.octo.demo.repository;

import com.octo.demo.exception.StarWarsException;
import com.octo.demo.exception.StarWarsNotFoundException;
import com.octo.demo.retrofit.StarWarsService;
import com.octo.demo.model.Character;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRepositoryImpl implements MyRepository {

    private static final String BASE_URL = "https://swapi.co/api/";

    @Override
    public Character findCharacterById(int id)
        throws StarWarsException, StarWarsNotFoundException {

        Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build();

        StarWarsService service = retrofit.create(StarWarsService.class);

        try {
            Character person = service.getCharacterById(id).execute().body();
            if (person == null) throw new StarWarsNotFoundException();
            return person;
        } catch (IOException e) {
            throw new StarWarsException();
        }
    }


}
