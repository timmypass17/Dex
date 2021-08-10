package android.example.dex.db.entity.set;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PokeSetImage {

    // Empty constructor needed by the parceler library
    public PokeSetImage() {}

    public String symbol;

    public String logo;

    public String getSymbol() {
        return symbol;
    }

    public String getLogo() {
        return logo;
    }
}
