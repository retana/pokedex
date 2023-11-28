package com.example.pokedex.resources;

import com.example.pokedex.model.PokemonFetchResults;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {
    @GET("pokemon/?limit=50")
    Call<PokemonFetchResults> getPokemons();
}