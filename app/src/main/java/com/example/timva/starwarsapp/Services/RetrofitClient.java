package com.example.timva.starwarsapp.Services;

import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.Data.StarWarsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitClient {
    @GET("people/")
    Call<StarWarsList<StarWarsCharacter>> getCharacterList();

    @GET("people/{id}/")
    Call<StarWarsCharacter> getCharacter(@Path("id")int characterId);
}
