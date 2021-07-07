package android.example.dex.db.entity.set;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PokeSetImage {

    // Empty constructor needed by the parceler library
    public PokeSetImage() {}

    @SerializedName("symbol")
    public String mSymbol;

    @SerializedName("logo")
    public String mLogo;

    public String getmSymbol() {
        return mSymbol;
    }

    public String getmLogo() {
        return mLogo;
    }
}
