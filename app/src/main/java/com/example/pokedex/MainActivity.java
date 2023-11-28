package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pokedex.model.PokemonFetchResults;
import com.example.pokedex.resources.DataAdapter;
import com.example.pokedex.resources.RestService;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        RestService restService = retrofit.create(RestService.class);
        Call<PokemonFetchResults> call = restService.getPokemons();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    PokemonFetchResults pokemonList =(PokemonFetchResults) response.body();
                    Log.d("INFORMACION",pokemonList.getResults().toString());
                    RecyclerView recyclerView = findViewById(R.id.rv_data);
                    if(Objects.nonNull(recyclerView)){
                        DataAdapter dataAdapter = new DataAdapter(pokemonList);
                        // Configura el LayoutManager y establece el Adapter en tu RecyclerView
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(dataAdapter);
                    }
                }else {
                    Log.d("Error", "Something happened");
                    return;
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("API_ERROR", t.toString());
            }
        });
        setContentView(R.layout.activity_main);
    }
}