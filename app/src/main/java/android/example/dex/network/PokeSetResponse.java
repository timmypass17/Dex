package android.example.dex.network;

import android.example.dex.db.entity.set.PokeSet;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokeSetResponse {

    @SerializedName("data")
    public List<PokeSet> pokeSets;

    public PokeSetResponse() {
        pokeSets = new ArrayList<>();
    }

    public List<PokeSet> getPokeSets() {
        return pokeSets;
    }
}
