package android.example.dex.models.pokemon;

import com.google.gson.annotations.SerializedName;

public class TCGPlayer {

    @SerializedName("prices")
    private Prices prices;

    public Prices getPrices() {
        return prices;
    }
}
