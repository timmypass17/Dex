package android.example.dex.api;

import android.example.dex.data.models.pokemon.Pokemon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokeResponse {

    @SerializedName("data")
    public List<Pokemon> pokemons;

    public PokeResponse() {
        pokemons = new ArrayList<Pokemon>();
    }

    // wtf is this for
    public static PokeResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        PokeResponse pokemonResponse = gson.fromJson(response, PokeResponse.class);
        return pokemonResponse;
    }

}
