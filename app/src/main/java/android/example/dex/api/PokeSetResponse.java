package android.example.dex.api;

import android.example.dex.models.pokemon.Pokemon;
import android.example.dex.models.set.PokeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokeSetResponse {
    @SerializedName("data")
    public List<PokeSet> pokeSets;

    public PokeSetResponse() {
        pokeSets = new ArrayList<PokeSet>();
    }
}
