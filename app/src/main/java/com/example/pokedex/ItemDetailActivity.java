package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_item_detail);

        Bundle extras = getIntent().getExtras();
        if(Objects.nonNull(extras)){
            int index = (int) extras.get("index");
            String name =  extras.get("name").toString();
            String description = extras.get("description").toString();
            this.setTitle(name);
            ImageView itemImage = this.findViewById(R.id.item_image);
            TextView textView = this.findViewById(R.id.txv_name);

            String pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + index + ".png";
            Glide.with(this)
                .load(pokemonImageUrl)
                .into(itemImage);
            textView.setText(description);

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}