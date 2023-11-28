package com.example.pokedex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonFetchResults {
   @SerializedName("results")
   @Expose
   private ArrayList<Pokemon> results;

   public ArrayList getResults() {
      return results;
   }
}