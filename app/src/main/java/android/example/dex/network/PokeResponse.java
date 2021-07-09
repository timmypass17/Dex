package android.example.dex.network;

import android.example.dex.db.entity.pokemon.Pokemon;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokeResponse {

    @SerializedName("data")
    public List<Pokemon> pokemons;

    public PokeResponse() {
        pokemons = new ArrayList<>();
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
// wat this for
//    public static PokeResponse parseJSON(String response) {
//        Gson gson = new GsonBuilder().create();
//        PokeResponse pokemonResponse = gson.fromJson(response, PokeResponse.class);
//        return pokemonResponse;
//    }

}
