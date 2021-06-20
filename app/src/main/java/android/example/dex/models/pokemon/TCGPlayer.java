package android.example.dex.models.pokemon;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TCGPlayer {

    public TCGPlayer() {}

    @SerializedName("url")
    private String url;

    @SerializedName("prices")
    private Prices prices;

    public String getUrl() {
        return url;
    }

    public Prices getPrices() {
        return prices;
    }
}
