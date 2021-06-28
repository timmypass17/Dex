package android.example.dex.data.models.pokemon;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TCGPlayer {

    @SerializedName("url")
    public String url;

    @Embedded
    @SerializedName("prices")
    public Prices prices;

    public TCGPlayer() {}

    public String getUrl() {
        return url;
    }

    public Prices getPrices() {
        return prices;
    }
}
