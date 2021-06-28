package android.example.dex.data.models.set;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PokeSetImage {

    // Empty constructor needed by the parceler library
    public PokeSetImage() {}

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
