package com.example.pokedex.resources;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.ItemDetailActivity;
import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonFetchResults;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private PokemonFetchResults pokemonList; // Asegúrate de que Pokemon sea la clase de tus datos

    // Constructor para recibir la lista de Pokémon
    public DataAdapter(PokemonFetchResults pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (pokemonList != null && position < pokemonList.getResults().size()) {
            // Accede a los datos y configura las vistas en el ViewHolder
            Pokemon elemento =(Pokemon) pokemonList.getResults().get(position);
            // Actualiza las vistas dentro del ViewHolder con los datos correspondientes
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index  = pokemonList.getResults().indexOf(elemento);
                    Pokemon item = elemento;
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);

                    intent.putExtra("index", index + 1);
                    intent.putExtra("name", item.getName());
                    intent.putExtra("description", item.getDescription());

                    context.startActivity(intent);
                }
            });
            holder.bind(elemento);
        }
    }

    @Override
    public int getItemCount() {
        return pokemonList.getResults().size();
    }

    // Aquí va el código para inflar el layout de cada elemento y mostrar los datos

    // Implementa los métodos necesarios de RecyclerView.Adapter, como onCreateViewHolder(), onBindViewHolder(), getItemCount(), etc.

    // Clase ViewHolder para mantener las vistas de cada elemento
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        // Aquí van las vistas de tu diseño de elemento individual (por ejemplo, nombre, imagen, etc.)
        // Asegúrate de inicializar y manejar las vistas aquí
        public ViewHolder(View itemView) {
            super(itemView);
            // Inicializa las vistas aquí
            nombreTextView = itemView.findViewById(R.id.text_nombre_pokemon);
        }
        public void bind(Pokemon pokemon) {
            nombreTextView.setText(pokemon.getName());
        }
    }
}