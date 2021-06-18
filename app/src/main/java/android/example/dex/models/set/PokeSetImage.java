package android.example.dex.models.set;

import com.google.gson.annotations.SerializedName;

public class PokeSetImage {

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("logo")
    private String logo;

    public String getSymbol() {
        return symbol;
    }

    public String getLogo() {
        return logo;
    }
}
